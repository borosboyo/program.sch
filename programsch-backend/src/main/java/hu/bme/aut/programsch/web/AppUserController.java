package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.AppUserDto;
import hu.bme.aut.programsch.model.AppUser;
import hu.bme.aut.programsch.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
public class AppUserController {
    @Autowired
    private AppUserService appUserService;

    @GetMapping(value = "/appUserEntity", produces = "application/json")
    public AppUser getAppUserEntity() {
        return appUserService.findAll().get(0);
    }

    @GetMapping(value = "/getFiltersEnabled", produces = "application/json")
    public boolean getAreFiltersEnabled() {
        return appUserService.findAllDto().get(0).isFiltersEnabled();
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
}

