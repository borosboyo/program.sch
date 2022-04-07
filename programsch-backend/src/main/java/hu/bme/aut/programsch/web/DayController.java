package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.model.Day;
import hu.bme.aut.programsch.service.DayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/day")
@RequiredArgsConstructor
public class DayController {

    private final DayService dayService;

    @GetMapping("/week")
    public List<Day> getCurrentWeek(@RequestBody LocalDate startOfWeek, @RequestBody LocalDate endOfWeek) {
        return dayService.getWeek(startOfWeek, endOfWeek);
    }
}
