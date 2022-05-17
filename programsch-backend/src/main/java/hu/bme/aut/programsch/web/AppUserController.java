package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.AppUserDto;
import hu.bme.aut.programsch.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/appuser")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping
    public ResponseEntity<AppUserDto> getAppUserEntity() {
        return ResponseEntity.ok(appUserService.findUser());
    }

}

