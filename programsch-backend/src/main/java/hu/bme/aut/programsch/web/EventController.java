package hu.bme.aut.programsch.web;

import hu.bme.aut.programsch.dto.CreateEventDto;
import hu.bme.aut.programsch.dto.EventDto;
import hu.bme.aut.programsch.dto.FullCalendarEventDto;
import hu.bme.aut.programsch.logging.executiontime.LogExecutionTime;
import hu.bme.aut.programsch.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    @Operation(summary = "Get Events",
            responses = {
                    @ApiResponse(description = "All of the Events",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EventDto.class)))),
                    @ApiResponse(responseCode = "400", description = "Events not found")})
    @LogExecutionTime
    public ResponseEntity<List<EventDto>> getEvents() {
        if(eventService.findAll().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(eventService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Event by ID",
            responses = {
                    @ApiResponse(description = "Event that was found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EventDto.class))),
                    @ApiResponse(responseCode = "400", description = "Event not found")})
    @LogExecutionTime
    public ResponseEntity<EventDto> getEventById(
            @Parameter(description = "The ID of the Event", required = true) @PathVariable Long id) {
        if(eventService.findById(id) == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(eventService.findById(id));
    }

    @GetMapping("/day")
    @Operation(summary = "Get Event by Date",
            responses = {
                    @ApiResponse(description = "Event that was found",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EventDto.class))),
                    @ApiResponse(responseCode = "400", description = "Event not found")})
    @LogExecutionTime
    public ResponseEntity<List<EventDto>> getEventsByDay(
            @Parameter(description = "The Date of the Event", required = true) @RequestParam String date) {
        if(eventService.findEventsByDay(date) == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(eventService.findEventsByDay(date));
    }

    @PostMapping
    @Operation(summary = "Create Event",
            responses = {
                    @ApiResponse(description = "Event that was created",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EventDto.class)))})
    @LogExecutionTime
    public ResponseEntity<EventDto> createEvent(
            @RequestBody(description = "Created Event object", required = true,
                    content = @Content(
                            schema = @Schema(implementation = EventDto.class))) @org.springframework.web.bind.annotation.RequestBody CreateEventDto createEventDto) {
        if(createEventDto == null) {
            return ResponseEntity.badRequest().build();
        }
        System.out.println("----");
        System.out.println(createEventDto);
        System.out.println("----");
        return ResponseEntity.ok(eventService.createEvent(createEventDto));
    }

    @GetMapping("/calendar")
    @Operation(summary = "Get the Events that can be consumed by FullCalendar",
            responses = {
                    @ApiResponse(description = "All of the Events",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = FullCalendarEventDto.class)))),
                    @ApiResponse(responseCode = "400", description = "Events not found")})
    @LogExecutionTime
    public ResponseEntity<List<FullCalendarEventDto>> getFullCalendarEvents() {
        if(eventService.findAllFullCalendarEvents().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(eventService.findAllFullCalendarEvents());
    }

    @GetMapping("/calendar/filtered")
    @Operation(summary = "Get the Events filtered by the User, that can be consumed by FullCalendar",
            responses = {
                    @ApiResponse(description = "All of the Events",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = FullCalendarEventDto.class)))),
                    @ApiResponse(responseCode = "400", description = "Events not found")})
    @LogExecutionTime
    public ResponseEntity<List<FullCalendarEventDto>> getFullCalendarFilteredEvents() {
        if(eventService.findAllFullCalendarFilteredEvents().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(eventService.findAllFullCalendarFilteredEvents());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Event by ID",
            responses = {
                    @ApiResponse(description = "Event that was deleted",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EventDto.class))),
                    @ApiResponse(responseCode = "400", description = "Event not found")})
    @LogExecutionTime
    public ResponseEntity<Void> deleteEvent(
            @Parameter(description = "The ID of the Event", required = true) @PathVariable long id) {
        if(eventService.findById(id) == null) {
            return ResponseEntity.badRequest().build();
        }
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Event",
            responses = {
                    @ApiResponse(description = "Event that was updated",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EventDto.class))),
                    @ApiResponse(responseCode = "400", description = "Event not found")})
    @LogExecutionTime
    public ResponseEntity<EventDto> updateEvent(
            @RequestBody(description = "Update Event object", required = true,
            content = @Content(
                    schema = @Schema(implementation = EventDto.class))) @org.springframework.web.bind.annotation.RequestBody CreateEventDto createEventDto) {
        if(eventService.updateEvent(createEventDto) == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(eventService.updateEvent(createEventDto));
    }
}
