package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.model.AppUserEntity;
import hu.bme.aut.programsch.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AppUserEntity getAppUserEntity() {
        return appUserService.findAll().get(0);
    }

    @GetMapping(value = "/getFiltersEnabled", produces = "application/json")
    public boolean getAreFiltersEnabled() {
        return appUserService.findAll().get(0).getFiltersEnabled();
    }

    @PostMapping("/enableFilters")
    public void enableFilters() {
        AppUserEntity appUser = appUserService.findAll().get(0);
        appUser.setFiltersEnabled(true);
        appUserService.save(appUser);
    }

    @PostMapping("/disableFilters")
    public void disableFilters() {
        AppUserEntity appUser = appUserService.findAll().get(0);
        appUser.setFiltersEnabled(false);
        appUserService.save(appUser);
    }
}
