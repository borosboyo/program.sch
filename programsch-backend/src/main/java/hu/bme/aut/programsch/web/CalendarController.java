package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.model.Calendar;
import hu.bme.aut.programsch.model.Day;
import hu.bme.aut.programsch.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping
    public Calendar getCalendar() {
        return calendarService.getCalendar();
    }

}
