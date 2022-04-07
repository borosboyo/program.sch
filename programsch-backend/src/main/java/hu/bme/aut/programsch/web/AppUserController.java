package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.AppUserDto;
import hu.bme.aut.programsch.mapper.AppUserMapper;
import hu.bme.aut.programsch.model.AppUser;
import hu.bme.aut.programsch.model.Filter;
import hu.bme.aut.programsch.service.AppUserService;
import hu.bme.aut.programsch.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

