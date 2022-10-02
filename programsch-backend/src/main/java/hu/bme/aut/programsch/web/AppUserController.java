package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.AppUserDto;
import hu.bme.aut.programsch.service.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Get AppUser",
            responses = {
                    @ApiResponse(description = "Get the current user",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AppUserDto.class)))),
                    @ApiResponse(responseCode = "400", description = "AppUser not found")})
    public ResponseEntity<AppUserDto> getAppUserEntity() {
        if(appUserService.findUser() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(appUserService.findUser());
    }
}

