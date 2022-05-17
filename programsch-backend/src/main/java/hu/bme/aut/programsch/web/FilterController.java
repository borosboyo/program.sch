package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.domain.Filter;
import hu.bme.aut.programsch.dto.FilterDto;
import hu.bme.aut.programsch.service.AppUserService;
import hu.bme.aut.programsch.service.FilterService;
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
    public ResponseEntity<FilterDto> changeUserFilters(@RequestBody FilterDto filterDto) {
        return new ResponseEntity<>(filterService.changeUserFilters(filterDto), HttpStatus.OK);
    }

    @GetMapping("/filtersEnabled")
    public ResponseEntity<Boolean> getAreFiltersEnabled() {
        FilterDto filter = filterService.findUserFilters(appUserService.findUser().getUid());
        return new ResponseEntity<>(filter != null, HttpStatus.OK);
    }

    @PostMapping("/enableFilters")
    public ResponseEntity<FilterDto> enableFilters() {
        return new ResponseEntity<>(filterService.createNewFilter(appUserService.findUser().getUid()), HttpStatus.OK);
    }

    @PostMapping("/disableFilters")
    public ResponseEntity<Void> disableFilters() {
        FilterDto filter = filterService.findUserFilters(appUserService.findUser().getUid());
        filterService.delete(filter);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<FilterDto> getUserFilters() {
        return new ResponseEntity<>(filterService.findUserFilters(appUserService.findUser().getUid()), HttpStatus.OK);
    }
}
