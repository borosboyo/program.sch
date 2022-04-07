package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.model.Calendar;
import hu.bme.aut.programsch.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
