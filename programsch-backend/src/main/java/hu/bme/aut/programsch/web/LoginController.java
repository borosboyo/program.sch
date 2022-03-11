package hu.bme.aut.programsch.web;

import com.google.common.hash.Hashing;
import hu.bme.aut.programsch.config.authsch.AuthSchAPI;
import hu.bme.aut.programsch.config.authsch.response.AuthResponse;
import hu.bme.aut.programsch.config.authsch.response.ProfileDataResponse;
import hu.bme.aut.programsch.config.authsch.struct.Entrant;
import hu.bme.aut.programsch.config.authsch.struct.PersonEntitlement;
import hu.bme.aut.programsch.config.authsch.struct.Scope;
import hu.bme.aut.programsch.model.AppUserEntity;
import hu.bme.aut.programsch.model.CardType;
import hu.bme.aut.programsch.service.AppUserService;
import hu.bme.aut.programsch.service.CircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    private AuthSchAPI authSchAPI;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private CircleService circleService;

    private final String USER_SESSION_ATTRIBUTE_NAME = "user_id";
    private final String USER_ENTITY_DTO_SESSION_ATTRIBUTE_NAME = "user";
    private final String CIRCLE_OWNERSHIP_SESSION_ATTRIBUTE_NAME = "circles";

    @GetMapping("/loggedin")
    public String loggedIn(@RequestParam String code, @RequestParam String state, HttpServletRequest request) throws Exception {
        if(!buildUniqueState(request).equals(state))
            throw new Exception("xd");

        Authentication auth = null;

        try {
            AuthResponse response = authSchAPI.validateAuthentication(code);
            ProfileDataResponse profile = authSchAPI.getProfile(response.getAccessToken());
            AppUserEntity appUser;
            List<Long> ownedCircles = getOwnedCircleIds(profile);
            if(appUserService.exists(profile.getInternalId().toString())){
                appUser = appUserService.getById(profile.getInternalId().toString());
                CardType card = cardTypeLookup(profile);
                if(appUser.getCardType() != card) {
                    appUser.setCardType(card);
                    appUserService.save(appUser);
                }
                List<String> permissionsByVIR = getCirclePermissionList(ownedCircles);
                if(!appUser.getPermissions().containsAll(permissionsByVIR)) {
                  permissionsByVIR.addAll(appUser.permissions);
                  appUserService.save(appUser);
                }

            } else {
                CardType card = cardTypeLookup(profile);
                appUser = new AppUserEntity(profile.getInternalId().toString(),
                        profile.getSurname() + " " + profile.getGivenName(),
                        profile.getMail(),
                        "",
                        card,
                        getCirclePermissionList(ownedCircles));
                appUserService.save(appUser);
                System.out.println(appUser.getName());
            }

            auth = new UsernamePasswordAuthenticationToken(code, state, getAuthorities(appUser));

            request.getSession().setAttribute(USER_SESSION_ATTRIBUTE_NAME, appUser.getUid());
            request.getSession().setAttribute(USER_ENTITY_DTO_SESSION_ATTRIBUTE_NAME, appUser);
            request.getSession().setAttribute(CIRCLE_OWNERSHIP_SESSION_ATTRIBUTE_NAME, ownedCircles);
            SecurityContextHolder.getContext().setAuthentication(auth);

        }  catch (Exception e) {
            auth.setAuthenticated(false);
            e.printStackTrace();
        }
        if (auth != null && auth.isAuthenticated()) {
            return "redirect:/";
        }
        else {
            return "redirect:/?error";
        }
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request)  {
        return "redirect:" + authSchAPI.generateLoginUrl(buildUniqueState(request),
                Scope.BASIC, Scope.GIVEN_NAME, Scope.SURNAME, Scope.MAIL, Scope.ENTRANTS, Scope.EDU_PERSON_ENTILEMENT);
    }

    private String buildUniqueState(HttpServletRequest request) {
        String s = (request.getSession().getId() + request.getLocalAddr());
        return Hashing.sha256().hashString(s, StandardCharsets.UTF_8).toString();
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession(false);
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        session.invalidate();
        for(Cookie cookie: request.getCookies()){
            cookie.setMaxAge(0);
        }
        request.removeAttribute(USER_SESSION_ATTRIBUTE_NAME);
        request.removeAttribute(USER_ENTITY_DTO_SESSION_ATTRIBUTE_NAME);
        request.removeAttribute(CIRCLE_OWNERSHIP_SESSION_ATTRIBUTE_NAME);
        request.getSession().removeAttribute(USER_SESSION_ATTRIBUTE_NAME);
        request.getSession().removeAttribute(USER_ENTITY_DTO_SESSION_ATTRIBUTE_NAME);
        request.getSession().removeAttribute(CIRCLE_OWNERSHIP_SESSION_ATTRIBUTE_NAME);
        request.changeSessionId();
        return "redirect:/?logged-out";
    }

    private List<Long> getOwnedCircleIds(ProfileDataResponse profile) {
        ArrayList<Long> ownedCircleIds = new ArrayList<>();
        for(PersonEntitlement pe : profile.getEduPersonEntitlements()){
            if(Objects.equals(pe.getStatus(), "körvezető")){
                ownedCircleIds.add(circleService.findByVirGroupId(pe.getId()).getId());
            }
        }
        return ownedCircleIds;
    }

    private List<String> getCirclePermissionList(List<Long> circles) {
        ArrayList<String> permissions = new ArrayList<>();
        if (!circles.isEmpty()) {
            permissions.add("ROLE_LEADER");
            for(Long l : circles){
                permissions.add("CIRCLE_" + l.toString());
            }
        }
        return permissions;
    }

    private CardType cardTypeLookup(ProfileDataResponse profile)  {
        CardType card = CardType.DO;
        for (Entrant entrant : profile.getEntrants()) {
            if (entrant.getEntrantType().equalsIgnoreCase("KB") && card.ordinal() < CardType.KB.ordinal()) {
                card = CardType.KB;
            } else if (entrant.getEntrantType().matches("^[ÁáAa][Bb]$") && card.ordinal() < CardType.AB.ordinal()) {
                card = CardType.AB;
            }
        }
        return card;
    }

    private List<GrantedAuthority> getAuthorities(AppUserEntity user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_${Role.USER.name}"));
        if (user.permissions.contains("ROLE_${Role.LEADER.name}"))
            authorities.add(new SimpleGrantedAuthority("ROLE_${Role.LEADER.name}"));
        return authorities;
    }
}
