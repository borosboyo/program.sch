package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.dto.CreateEventDto;
import hu.bme.aut.programsch.dto.EventDto;
import hu.bme.aut.programsch.dto.FullCalendarEventDto;
import hu.bme.aut.programsch.mapper.EventMapper;
import hu.bme.aut.programsch.mapper.FullCalendarEventDtoMapper;
import hu.bme.aut.programsch.model.Event;
import hu.bme.aut.programsch.repository.CircleRepository;
import hu.bme.aut.programsch.repository.DayRepository;
import hu.bme.aut.programsch.repository.EventRepository;
import hu.bme.aut.programsch.repository.ResortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final EventRepository eventRepository;
    private final DayRepository dayRepository;
    private final CircleRepository circleRepository;
    private final ResortRepository resortRepository;

    private final EventMapper eventMapper;
    private final FullCalendarEventDtoMapper fullCalendarEventDtoMapper;

    @Transactional
    public List<EventDto> findAll() {
        return eventMapper.eventsToDtos(eventRepository.findAll());
    }

    @Transactional
    public EventDto createEvent(CreateEventDto createEventDto) {
        Event newEvent = new Event();
        newEvent.setCircle(circleRepository.findByDisplayName(createEventDto.getCircle()));
        newEvent.setName(createEventDto.getName());

        createEventDto.setStart(createEventDto.getStart().replace("T", " "));
        createEventDto.setEnd(createEventDto.getEnd().replace("T", " "));

        newEvent.setStart(LocalDateTime.parse(createEventDto.getStart(), formatter));
        newEvent.setEnd(LocalDateTime.parse(createEventDto.getEnd(), formatter));
        newEvent.setPlace(createEventDto.getPlace());
        newEvent.setFacebookUrl(createEventDto.getFacebookUrl());
        newEvent.setPoster(createEventDto.getPoster());
        newEvent.setTldr(createEventDto.getTldr());
        newEvent.setDescription(createEventDto.getDescription());

        return eventMapper.eventToDto(eventRepository.save(newEvent));
    }

    @Transactional
    public List<FullCalendarEventDto> findAllFullCalendarEvents() {
        List<FullCalendarEventDto> events = fullCalendarEventDtoMapper.eventsToDtos(eventRepository.findAll());
        for(FullCalendarEventDto e : events) {
            e.setDate(e.getStart().toLocalDate());
        }
        return events;
    }

    @Transactional
    public EventDto findById(Long id) {
        return eventMapper.eventToDto(eventRepository.findById(id).get());
    }
}
