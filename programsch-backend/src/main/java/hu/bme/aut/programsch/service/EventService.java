package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.dto.*;
import hu.bme.aut.programsch.mapper.EventMapper;
import hu.bme.aut.programsch.mapper.FullCalendarEventDtoMapper;
import hu.bme.aut.programsch.domain.EmailType;
import hu.bme.aut.programsch.domain.Event;
import hu.bme.aut.programsch.domain.Mail;
import hu.bme.aut.programsch.repository.CircleRepository;
import hu.bme.aut.programsch.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class EventService {
    private static final Logger logger = Logger.getLogger(EventService.class.getName());
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final EventRepository eventRepository;
    private final CircleRepository circleRepository;

    private final AppUserService appUserService;
    private final EmailService emailService;
    private final FilterService filterService;

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

        configureNewEvent(createEventDto, newEvent);

        try {
            Mail mail = emailService.createMail(appUserService.findUser().getEmail(), EmailType.NEWEVENT, Map.of("event", newEvent));
            emailService.sendMail(mail);
        } catch (MessagingException | UnsupportedEncodingException e) {
             logger.log(Level.SEVERE, "Error while sending email", e);
        }

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
    public List<FullCalendarEventDto> findAllFullCalendarFilteredEvents() {
        List<Event> events = eventRepository.findAll();
        FilterDto filter = new FilterDto();
        if(appUserService.findUser() != null) {
            filter = filterService.findUserFilters(appUserService.findUser().getUid());
        }

        if(filter != null) {
            FilterDto finalFilter = filter;
            events.removeIf(e -> finalFilter.getFilteredCircles().contains(e.getCircle().getDisplayName()));
        }

        List<FullCalendarEventDto> filteredEvents = fullCalendarEventDtoMapper.eventsToDtos(events);

        for(FullCalendarEventDto e : filteredEvents) {
            e.setDate(e.getStart().toLocalDate());
        }
        return filteredEvents;
    }

    @Transactional
    public EventDto findById(Long id) {
        if(eventRepository.findById(id).isPresent()) {
            return eventMapper.eventToDto(eventRepository.findById(id).get());
        }
        return null;
    }

    @Transactional
    public List<EventDto> findEventsByDay(String day) {
        List<Event> events = eventRepository.findAll();

        for(Iterator<Event> iterator = events.iterator(); iterator.hasNext();) {
            Event e = iterator.next();
            String monthWithStartDay = e.getStart().getMonth().getValue() + "-" + e.getStart().getDayOfMonth();
            String monthWithEndDay = e.getEnd().getMonth().getValue() + "-" + e.getEnd().getDayOfMonth();
            if(!monthWithStartDay.equals(day) && !monthWithEndDay.equals(day)) {
                iterator.remove();
            }
        }
        return eventMapper.eventsToDtos(events);
    }

    @Transactional
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Transactional
    public EventDto updateEvent(CreateEventDto createEventDto) {
        Event event = eventRepository.findById(createEventDto.getId()).orElseThrow();
        event.setName(createEventDto.getName());
        event.setCircle(circleRepository.findByDisplayName(createEventDto.getCircle()));

        configureNewEvent(createEventDto, event);

        return eventMapper.eventToDto(eventRepository.save(event));
    }

    private void configureNewEvent(CreateEventDto createEventDto, Event event) {
        createEventDto.setStart(createEventDto.getStart().replace("T", " "));
        createEventDto.setEnd(createEventDto.getEnd().replace("T", " "));
        SetEventParameterDto setEventParameterDto = new SetEventParameterDto(LocalDateTime.parse(createEventDto.getStart(), formatter), LocalDateTime.parse(createEventDto.getEnd(), formatter), createEventDto.getPlace(), createEventDto.getFacebookUrl(), createEventDto.getPoster(), createEventDto.getTldr(), createEventDto.getDescription());
        setEventParameters(event, setEventParameterDto);
    }

    private void setEventParameters(Event event, SetEventParameterDto setEventParameterDto) {
        event.setStart(setEventParameterDto.getParse());
        event.setEnd(setEventParameterDto.getParse2());
        event.setPlace(setEventParameterDto.getPlace());
        event.setFacebookUrl(setEventParameterDto.getFacebookUrl());
        event.setPoster(setEventParameterDto.getPoster());
        event.setTldr(setEventParameterDto.getTldr());
        event.setDescription(setEventParameterDto.getDescription());
    }
}
