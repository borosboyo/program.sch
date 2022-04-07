package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.AppUserDto;
import hu.bme.aut.programsch.model.AppUser;
import hu.bme.aut.programsch.model.Filter;
import hu.bme.aut.programsch.service.AppUserService;
import hu.bme.aut.programsch.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    private final FilterService filterService;

    @GetMapping(value = "/appUser", produces = "application/json")
    public AppUser getAppUserEntity() {
        return appUserService.findAll().get(0);
    }

    @GetMapping(value = "/getFiltersEnabled", produces = "application/json")
    public boolean getAreFiltersEnabled() {
        Filter filter = filterService.findUserFilters(appUserService.findAll().get(0).getUid());
        if (filter != null) {
            return true;
        }
        return false;
    }

    @PostMapping("/enableFilters")
    public ResponseEntity<AppUserDto> enableFilters() {
        AppUserDto appUserDto = appUserService.findAllDto().get(0);
        appUserDto.setFiltersEnabled(true);
        appUserService.save(appUserDto);
        return new ResponseEntity<>(appUserService.save(appUserDto), HttpStatus.OK);
    }

    @PostMapping("/disableFilters")
    public void disableFilters() {
        AppUserDto appUser = appUserService.findAllDto().get(0);
        appUser.setFiltersEnabled(false);
        appUserService.save(appUser);
    }

    @GetMapping("/appuser/filters")
    public Filter getUserFilters() {
        System.out.println(appUserService.findUserFilters(appUserService.findAll().get(0).getUid()));
        return appUserService.findUserFilters(appUserService.findAll().get(0).getUid());
    }
}

