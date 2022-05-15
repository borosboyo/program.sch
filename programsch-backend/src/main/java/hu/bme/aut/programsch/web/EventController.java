package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.CreateEventDto;
import hu.bme.aut.programsch.dto.EventDto;
import hu.bme.aut.programsch.dto.FullCalendarEventDto;
import hu.bme.aut.programsch.mapper.EventMapper;
import hu.bme.aut.programsch.model.Event;
import hu.bme.aut.programsch.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public List<EventDto> getEvents() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public EventDto getEventById(@PathVariable Long id) {
        return eventService.findById(id);
    }

    @PostMapping
    public EventDto createEvent(@RequestBody CreateEventDto createEventDto) {
        return eventService.createEvent(createEventDto);
    }

    @GetMapping("/calendar")
    public List<FullCalendarEventDto> getFullCalendarEvents() {
        return eventService.findAllFullCalendarEvents();
    }
}
