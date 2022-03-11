package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.DayDto;
import hu.bme.aut.programsch.service.DayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/days")
@RequiredArgsConstructor
public class DayController {
    private final DayService dayService;

    @GetMapping
    public List<DayDto> getDays() {
        return dayService.findAll();
    }

    @GetMapping("/{id}")
    public DayDto getDayById(@PathVariable long id) {
        return dayService.findById(id);
    }

    @PostMapping
    public DayDto createDay(@RequestBody DayDto dayDto) {
        return dayService.createDay(dayDto);
    }

    @DeleteMapping
    public void deleteAllDays() {
        dayService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteDay(@PathVariable long id) {
        dayService.deleteDay(id);
    }

    @PutMapping("/{id}")
    public DayDto updateDay(@RequestBody DayDto dayDto) {
        return dayService.updateDay(dayDto);
    }
}
