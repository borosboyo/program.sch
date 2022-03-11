package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.ResortDto;
import hu.bme.aut.programsch.service.ResortService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resorts")
@RequiredArgsConstructor
public class ResortController {
    private final ResortService resortService;

    @GetMapping
    public List<ResortDto> getResorts() {
        return resortService.findAll();
    }

    @GetMapping("/{id}")
    public ResortDto getResortById(@PathVariable long id) {
        return resortService.findById(id);
    }

    @PostMapping
    public ResortDto createResort(@RequestBody ResortDto resortDto) {
        return resortService.createResort(resortDto);
    }

    @DeleteMapping
    public void deleteAllResorts() {
        resortService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteResort(@PathVariable long id) {
        resortService.deleteResort(id);
    }

    @PutMapping("/{id}")
    public ResortDto updateResort(@RequestBody ResortDto resortDto) {
        return resortService.updateResort(resortDto);
    }
}
