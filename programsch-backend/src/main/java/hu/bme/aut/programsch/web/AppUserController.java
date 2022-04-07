package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.AppUserDto;
import hu.bme.aut.programsch.service.AppUserService;
import hu.bme.aut.programsch.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/appuser")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    private final FilterService filterService;

    @GetMapping
    public AppUserDto getAppUserEntity() {
        return appUserService.findUser();
    }

}

