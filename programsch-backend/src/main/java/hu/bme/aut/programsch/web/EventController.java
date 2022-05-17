package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.CreateEventDto;
import hu.bme.aut.programsch.dto.EventDto;
import hu.bme.aut.programsch.dto.FullCalendarEventDto;
import hu.bme.aut.programsch.service.EventService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventDto>> getEvents() {
        return ResponseEntity.ok(eventService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.findById(id));
    }

    @GetMapping("/day")
    public ResponseEntity<List<EventDto>> getEventsByDay(@RequestParam String date) {
        return ResponseEntity.ok(eventService.findEventsByDay(date));
    }

    @PostMapping
    public ResponseEntity<EventDto> createEvent(@RequestBody CreateEventDto createEventDto) {
        return ResponseEntity.ok(eventService.createEvent(createEventDto));
    }

    @GetMapping("/calendar")
    public ResponseEntity<List<FullCalendarEventDto>> getFullCalendarEvents() {
        return ResponseEntity.ok(eventService.findAllFullCalendarEvents());
    }

    @GetMapping("/calendar/filtered")
    public ResponseEntity<List<FullCalendarEventDto>> getFullCalendarFilteredEvents() {
        return ResponseEntity.ok(eventService.findAllFullCalendarFilteredEvents());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDto> updateEvent(@RequestBody CreateEventDto createEventDto) {
        System.out.println("asdasdasdasdasADSSSSSSSSSS");
        return ResponseEntity.ok(eventService.updateEvent(createEventDto));
    }
}
