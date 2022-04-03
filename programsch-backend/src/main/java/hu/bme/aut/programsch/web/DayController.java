package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.model.Day;
import hu.bme.aut.programsch.service.DayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class DayController {

    private final DayService dayService;

    @GetMapping("/week")
    public List<Day> getCurrentWeek(@RequestBody LocalDate startOfWeek, @RequestBody LocalDate endOfWeek) {
        return dayService.getWeek(startOfWeek, endOfWeek);
    }
}
