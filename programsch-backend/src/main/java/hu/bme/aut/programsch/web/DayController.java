package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.model.Day;
import hu.bme.aut.programsch.service.DayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/day")
@RequiredArgsConstructor
public class DayController {

    private final DayService dayService;

    @GetMapping("/currentWeek")
    public List<Day> getCurrentWeek() {
        return dayService.getWeek(LocalDate.parse("2022-04-04"), LocalDate.parse("2022-04-10"));
    }
}
