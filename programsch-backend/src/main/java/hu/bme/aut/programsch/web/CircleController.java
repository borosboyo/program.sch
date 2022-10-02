package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.CircleDto;
import hu.bme.aut.programsch.logging.LogExecutionTime;
import hu.bme.aut.programsch.service.CircleService;
import hu.bme.aut.programsch.service.ResortService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/circle")
@RequiredArgsConstructor
public class CircleController {

    private final CircleService circleService;

    @GetMapping()
    @Operation(summary = "Get Circles",
            responses = {
                    @ApiResponse(description = "All of the Circles",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CircleDto.class)))),
                    @ApiResponse(responseCode = "400", description = "Circles not found")})
    @LogExecutionTime
    public ResponseEntity<List<CircleDto>> getCircles() {
        if(circleService.findAll().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(circleService.findAll());
    }
}
