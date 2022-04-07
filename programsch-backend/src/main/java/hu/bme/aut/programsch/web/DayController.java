package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.DayDto;
import hu.bme.aut.programsch.model.Day;
import hu.bme.aut.programsch.service.DayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/day")
@RequiredArgsConstructor
public class DayController {

    private final DayService dayService;

    @GetMapping("/currentweek")
    public List<DayDto> getCurrentWeek(@RequestParam String startOfWeek, @RequestParam String endOfWeek) {
        return dayService.getWeek(LocalDate.parse(startOfWeek), LocalDate.parse(endOfWeek));
    }
}
