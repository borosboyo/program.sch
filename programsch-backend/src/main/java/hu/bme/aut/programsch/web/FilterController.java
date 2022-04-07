package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.AppUserDto;
import hu.bme.aut.programsch.model.Filter;
import hu.bme.aut.programsch.service.AppUserService;
import hu.bme.aut.programsch.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/filter")
@RequiredArgsConstructor
public class FilterController {

    private final FilterService filterService;
    private final AppUserService appUserService;

    @GetMapping("/filtersEnabled")
    public boolean getAreFiltersEnabled() {
        Filter filter = filterService.findUserFilters(appUserService.findUser().getUid());
        return filter != null;
    }

    @PostMapping("/enableFilters")
    public ResponseEntity<Filter> enableFilters() {
        return new ResponseEntity<>(filterService.createNewFilter(appUserService.findUser().getUid()), HttpStatus.OK);
    }

    @PostMapping("/disableFilters")
    public ResponseEntity<Void> disableFilters() {
        Filter filter = filterService.findUserFilters(appUserService.findUser().getUid());
        filterService.delete(filter);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping
    public Filter getUserFilters() {
        System.out.println(appUserService.findUserFilters(appUserService.findUser().getUid()));
        return appUserService.findUserFilters(appUserService.findUser().getUid());
    }
}
