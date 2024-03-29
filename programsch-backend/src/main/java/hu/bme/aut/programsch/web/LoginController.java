package hu.bme.aut.programsch.web;

import com.google.common.hash.Hashing;
import hu.bme.aut.programsch.config.authsch.AuthSchAPI;
import hu.bme.aut.programsch.config.authsch.response.AuthResponse;
import hu.bme.aut.programsch.config.authsch.response.ProfileDataResponse;
import hu.bme.aut.programsch.config.authsch.struct.PersonEntitlement;
import hu.bme.aut.programsch.config.authsch.struct.Scope;
import hu.bme.aut.programsch.domain.AppUser;
import hu.bme.aut.programsch.domain.LoginUrl;
import hu.bme.aut.programsch.logging.executiontime.LogExecutionTime;
import hu.bme.aut.programsch.service.AppUserService;
import hu.bme.aut.programsch.service.CircleService;
import hu.bme.aut.programsch.service.MembershipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private static final String USER_SESSION_ATTRIBUTE_NAME = "user_id";
    private static final String USER_ENTITY_DTO_SESSION_ATTRIBUTE_NAME = "user";
    private static final String CIRCLE_OWNERSHIP_SESSION_ATTRIBUTE_NAME = "circles";

    private static final Logger logger = Logger.getLogger(LoginController.class.getName());

    private final AppUserService appUserService;
    private final AuthSchAPI authSchAPI;
    private final CircleService circleService;
    private final MembershipService memberShipService;

    private boolean loggedIn = false;

    @GetMapping("/loggedin")
    @Operation(summary = "The redirect from the login page")
    @LogExecutionTime
    public ResponseEntity<Void> loggedIn(@RequestParam String code, @RequestParam String state, HttpServletRequest request) {
        Authentication auth = null;
        try {
            AuthResponse response = authSchAPI.validateAuthentication(code);
            ProfileDataResponse profile = authSchAPI.getProfile(response.getAccessToken());
            memberShipService.addMemberships(profile);
            AppUser appUser;
            List<Long> ownedCircles = getOwnedCircleIds(profile);
            if (appUserService.exists(profile.getInternalId().toString())) {
                appUser = appUserService.getById(profile.getInternalId().toString());
                List<String> permissionsByVIR = getCirclePermissionList(ownedCircles);
                if (!appUser.getPermissions().containsAll(permissionsByVIR)) {
                    permissionsByVIR.addAll(appUser.getPermissions());
                    appUserService.save(appUser);
                }

            } else {
                appUser = new AppUser(profile.getInternalId().toString(),
                        profile.getSurname() + " " + profile.getGivenName(),
                        profile.getMail(),
                        "",
                        getCirclePermissionList(ownedCircles));
                appUserService.save(appUser);
            }


            auth = new UsernamePasswordAuthenticationToken(code, state, getAuthorities(appUser));

            request.getSession().setAttribute(USER_SESSION_ATTRIBUTE_NAME, appUser.getUid());
            request.getSession().setAttribute(USER_ENTITY_DTO_SESSION_ATTRIBUTE_NAME, appUser);
            request.getSession().setAttribute(CIRCLE_OWNERSHIP_SESSION_ATTRIBUTE_NAME, ownedCircles);
            SecurityContextHolder.getContext().setAuthentication(auth);
            loggedIn = true;
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create("http://localhost:3000"))
                    .build();
        } catch (Exception e) {
            if (auth != null) {
                auth.setAuthenticated(false);
            }
           logger.log(java.util.logging.Level.SEVERE, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .location(URI.create(""))
                .build();
    }

    @GetMapping(value = "/login")
    @Operation(summary = "Login to the app",
            responses = {
                    @ApiResponse(description = "The OAuth2 authorization URL",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = LoginUrl.class))))})
    @LogExecutionTime
    public ResponseEntity<LoginUrl> getLoginInfo(HttpServletRequest request) {
        return new ResponseEntity<>(new LoginUrl(authSchAPI.generateLoginUrl(buildUniqueState(request),
                Scope.BASIC, Scope.GIVEN_NAME, Scope.SURNAME, Scope.MAIL, Scope.ENTRANTS, Scope.EDU_PERSON_ENTILEMENT)), HttpStatus.OK);
    }

    private String buildUniqueState(HttpServletRequest request) {
        String s = (request.getSession().getId() + request.getLocalAddr());
        return Hashing.sha256().hashString(s, StandardCharsets.UTF_8).toString();
    }

    @GetMapping("/logout")
    @Operation(summary = "Logout of the app")
    @LogExecutionTime
    public void logout(HttpServletRequest request) {
        request.getSession(false);
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                cookie.setMaxAge(0);
            }
        }

        request.removeAttribute(USER_SESSION_ATTRIBUTE_NAME);
        request.removeAttribute(USER_ENTITY_DTO_SESSION_ATTRIBUTE_NAME);
        request.removeAttribute(CIRCLE_OWNERSHIP_SESSION_ATTRIBUTE_NAME);
        request.getSession().removeAttribute(USER_SESSION_ATTRIBUTE_NAME);
        request.getSession().removeAttribute(USER_ENTITY_DTO_SESSION_ATTRIBUTE_NAME);
        request.getSession().removeAttribute(CIRCLE_OWNERSHIP_SESSION_ATTRIBUTE_NAME);
        request.changeSessionId();
        loggedIn = false;
    }

    private List<Long> getOwnedCircleIds(ProfileDataResponse profile) {
        ArrayList<Long> ownedCircleIds = new ArrayList<>();
        for (PersonEntitlement pe : profile.getEduPersonEntitlements()) {
            if (Objects.equals(pe.getStatus(), "körvezető")) {
                ownedCircleIds.add(circleService.findByVirGroupId(pe.getId()).getId());
            }
        }
        return ownedCircleIds;
    }

    private List<String> getCirclePermissionList(List<Long> circles) {
        ArrayList<String> permissions = new ArrayList<>();
        if (!circles.isEmpty()) {
            permissions.add("ROLE_LEADER");
            for (Long l : circles) {
                permissions.add("CIRCLE_" + l.toString());
            }
        }
        return permissions;
    }

    private List<GrantedAuthority> getAuthorities(AppUser user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_${Role.USER.name}"));
        if (user.getPermissions().contains("ROLE_${Role.LEADER.name}"))
            authorities.add(new SimpleGrantedAuthority("ROLE_${Role.LEADER.name}"));
        return authorities;
    }

    @GetMapping(value = "/isLoggedIn", produces = "application/json")
    @Operation(summary = "Get Login state",
            responses = {
                    @ApiResponse(description = "State of the login",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Boolean.class))))})
    @LogExecutionTime
    public ResponseEntity<Boolean> getIsLoggedIn() {
        return new ResponseEntity<>(loggedIn, HttpStatus.OK);
    }
}
