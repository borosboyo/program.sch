package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.model.Calendar;
import hu.bme.aut.programsch.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping("/calendar")
    public int getCalendar() {
        System.out.println(calendarService.getCalendar().getDays().size());
        return calendarService.getCalendar().getDays().size();
    }

}
