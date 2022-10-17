package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.domain.Filter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import hu.bme.aut.programsch.dto.FilterDto;
import hu.bme.aut.programsch.service.AppUserService;
import hu.bme.aut.programsch.service.FilterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filter")
@RequiredArgsConstructor
public class FilterController {

    private final FilterService filterService;
    private final AppUserService appUserService;

    @PostMapping
    @Operation(summary = "Change user filters",
            responses = {
                    @ApiResponse(description = "The filter that was changed",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = FilterDto.class))),
                    @ApiResponse(responseCode = "400", description = "Filter not found")})
    public ResponseEntity<FilterDto> changeUserFilters(
            @RequestBody(description = "The filter object", required = true,
                    content = @Content(
                            schema = @Schema(implementation = FilterDto.class))) @org.springframework.web.bind.annotation.RequestBody FilterDto filterDto) {
        if(filterService.findUserFilters(filterDto.getUserId()) == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(filterService.changeUserFilters(filterDto), HttpStatus.OK);
    }

    @GetMapping("/filtersEnabled")
    @Operation(summary = "Get Filter state",
            responses = {
                    @ApiResponse(description = "The state of the filter for the current user.",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Boolean.class))))})
    public ResponseEntity<Boolean> getAreFiltersEnabled() {
        FilterDto filterDto = null;
        if(appUserService.findUser() != null) {
            filterDto = filterService.findUserFilters(appUserService.findUser().getUid());
        }
        return new ResponseEntity<>( filterDto != null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/enableFilters")
    @Operation(summary = "Enable Filters",
            responses = {
                    @ApiResponse(description = "The filter that was enabled",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = FilterDto.class)))})
    public ResponseEntity<FilterDto> enableFilters() {
        return new ResponseEntity<>(filterService.createNewFilter(appUserService.findUser().getUid()), HttpStatus.OK);
    }

    @PostMapping("/disableFilters")
    @Operation(summary = "Disable filters",
            responses = {
                    @ApiResponse(description = "Void",
                            content = @Content(mediaType = "application/json"))})
    public ResponseEntity<Void> disableFilters() {
        FilterDto filter = filterService.findUserFilters(appUserService.findUser().getUid());
        filterService.delete(filter);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get user filters",
            responses = {
                    @ApiResponse(description = "The filter that the user has",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = FilterDto.class)))),
                    @ApiResponse(responseCode = "400", description = "Filter not found")})
    public ResponseEntity<FilterDto> getUserFilters() {
        if(filterService.findUserFilters(appUserService.findUser().getUid()) == null) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(filterService.findUserFilters(appUserService.findUser().getUid()), HttpStatus.OK);
    }
}
