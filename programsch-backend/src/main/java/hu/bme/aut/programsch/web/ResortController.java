package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.ResortDto;
import hu.bme.aut.programsch.model.Resort;
import hu.bme.aut.programsch.service.ResortService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/resort")
@RequiredArgsConstructor
public class ResortController {

    private final ResortService resortService;

    @GetMapping
    public List<ResortDto> getResorts() {
        return resortService.findAll();
    }
}
