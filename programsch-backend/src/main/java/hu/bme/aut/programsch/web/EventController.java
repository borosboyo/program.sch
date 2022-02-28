package hu.bme.aut.programsch.web;


import hu.bme.aut.programsch.dto.EventDto;
import hu.bme.aut.programsch.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public List<EventDto> getEvents() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public EventDto getEventById(@PathVariable long id) {
        return eventService.findById(id);
    }

    @PostMapping
    public EventDto createEvent(@RequestBody EventDto eventDto) {
        return eventService.createEvent(eventDto);
    }

    @DeleteMapping
    public void deleteAllEvents() {
        eventService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable long id) {
        eventService.deleteEvent(id);
    }

    @PutMapping("/{id}")
    public EventDto updateEvent(@PathVariable long id) {
        //return eventService.updateEvent(eventService.findById(id));
    }
}
