package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.ResortDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/resort")
@RequiredArgsConstructor
public class ResortController {

    private final ResortService resortService;

    @GetMapping
    @Operation(summary = "Get Resorts",
            responses = {
                    @ApiResponse(description = "All of the Resorts",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResortDto.class))))})
    public List<ResortDto> getResorts() {
        return resortService.findAll();
    }

    @GetMapping("/{name}")
    @Operation(summary = "Get Resort by Name",
            responses = {
                    @ApiResponse(description = "Name that was found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResortDto.class))),
                    @ApiResponse(responseCode = "400", description = "Resort not found")})
    public ResponseEntity<ResortDto> getResortByName(
            @Parameter(description = "The Name of the Resort", required = true) @PathVariable String name) {
        return new ResponseEntity<>(resortService.findByName(name), HttpStatus.FOUND);
    }
}
