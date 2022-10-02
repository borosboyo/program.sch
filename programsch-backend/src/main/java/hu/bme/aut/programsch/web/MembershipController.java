package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.EventDto;
import hu.bme.aut.programsch.dto.MembershipDto;
import hu.bme.aut.programsch.logging.LogExecutionTime;
import hu.bme.aut.programsch.service.AppUserService;
import hu.bme.aut.programsch.service.MembershipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/membership")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    @GetMapping("/{appUserUid}")
    @Operation(summary = "Get Memberships by User ID",
            responses = {
                    @ApiResponse(description = "Memberships that were found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EventDto.class))),
                    @ApiResponse(responseCode = "400", description = "Memberships not found")})
    @LogExecutionTime
    public ResponseEntity<List<MembershipDto>> getMembershipsByAppUserUid(@PathVariable String appUserUid) {
        if(membershipService.getMembershipsByAppUserUid(appUserUid).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(membershipService.getMembershipsByAppUserUid(appUserUid), HttpStatus.OK);
    }
}
