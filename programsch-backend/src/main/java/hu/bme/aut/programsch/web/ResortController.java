package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.ResortDto;
import hu.bme.aut.programsch.logging.executiontime.LogExecutionTime;
import hu.bme.aut.programsch.service.ResortService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resort")
@RequiredArgsConstructor
public class    ResortController {

    private final ResortService resortService;

    @GetMapping
    @Operation(summary = "Get Resorts",
            responses = {
                    @ApiResponse(description = "All of the Resorts",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResortDto.class)))),
                    @ApiResponse(responseCode = "400", description = "Resorts not found")})
    @LogExecutionTime
    public ResponseEntity<List<ResortDto>> getResorts() {
        if(resortService.findAll().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(resortService.findAll());
    }

    @GetMapping("/{name}")
    @Operation(summary = "Get Resort by Name",
            responses = {
                    @ApiResponse(description = "Name that was found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResortDto.class))),
                    @ApiResponse(responseCode = "400", description = "Resort not found")})
    @LogExecutionTime
    public ResponseEntity<ResortDto> getResortByName(
            @Parameter(description = "The Name of the Resort", required = true) @PathVariable String name) {
        if(name == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(resortService.findByName(name), HttpStatus.FOUND);
    }

    @GetMapping("/usermemberships")
    @Operation(summary = "Get the Resorts by the Circles that the User has membership in",
            responses = {
                    @ApiResponse(description = "All of the Resorts that were found",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResortDto.class)))),
                    @ApiResponse(responseCode = "400", description = "Resorts not found")})
    @LogExecutionTime
    public ResponseEntity<List<ResortDto>> getResortsWithUserMemberships() {
        return ResponseEntity.ok(resortService.findByMemberships());
    }

    @GetMapping("/byCircle")
    @Operation(summary = "Get Resort by Circle",
            responses = {
                    @ApiResponse(description = "The Resort",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResortDto.class)))),
                    @ApiResponse(responseCode = "400", description = "Resort not found")})
    public ResponseEntity<ResortDto> getResortByCircle(@RequestParam String circleName) {
        if(resortService.findResortByCircle(circleName) == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(resortService.findResortByCircle(circleName));
    }
}
