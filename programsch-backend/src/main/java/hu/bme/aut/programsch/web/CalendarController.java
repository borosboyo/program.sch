package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.CalendarDto;
import hu.bme.aut.programsch.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendars")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @GetMapping
    public List<CalendarDto> getCalendars() {
        return calendarService.findAll();
    }

    @GetMapping("/{id}")
    public CalendarDto getCalendarById(@PathVariable long id) {
        return calendarService.findById(id);
    }

    @PostMapping
    public CalendarDto createCalendar(@RequestBody CalendarDto calendarDto) {
        return calendarService.createCalendar(calendarDto);
    }

    @DeleteMapping
    public void deleteAllCalendars() {
        calendarService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteCalendar(@PathVariable long id) {
        calendarService.deleteCalendar(id);
    }

    @PutMapping("/{id}")
    public CalendarDto updateCalendar(@PathVariable long id) {
        //return calendarService.updateCalendar(calendarService.findById(id));
    }
}
