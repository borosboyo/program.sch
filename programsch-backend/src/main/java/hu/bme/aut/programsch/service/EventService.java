package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.dto.CreateEventDto;
import hu.bme.aut.programsch.dto.EventDto;
import hu.bme.aut.programsch.dto.FilterDto;
import hu.bme.aut.programsch.dto.FullCalendarEventDto;
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

@Service
@RequiredArgsConstructor
public class EventService {
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
            e.printStackTrace();
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
        FilterDto filter = filterService.findUserFilters(appUserService.findUser().getUid());

        events.removeIf(e -> filter.getFilteredCircles().contains(e.getCircle().getDisplayName()));

        List<FullCalendarEventDto> filteredEvents = fullCalendarEventDtoMapper.eventsToDtos(events);

        for(FullCalendarEventDto e : filteredEvents) {
            e.setDate(e.getStart().toLocalDate());
        }
        return filteredEvents;
    }

    @Transactional
    public EventDto findById(Long id) {
        return eventMapper.eventToDto(eventRepository.findById(id).get());
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
        Event event = eventRepository.findById(createEventDto.getId()).get();
        event.setName(createEventDto.getName());
        event.setCircle(circleRepository.findByDisplayName(createEventDto.getCircle()));

        configureNewEvent(createEventDto, event);

        return eventMapper.eventToDto(eventRepository.save(event));
    }

    private void configureNewEvent(CreateEventDto createEventDto, Event event) {
        createEventDto.setStart(createEventDto.getStart().replace("T", " "));
        createEventDto.setEnd(createEventDto.getEnd().replace("T", " "));
        setEventParameters(event, LocalDateTime.parse(createEventDto.getStart(), formatter), LocalDateTime.parse(createEventDto.getEnd(), formatter), createEventDto.getPlace(), createEventDto.getFacebookUrl(), createEventDto.getPoster(), createEventDto.getTldr(), createEventDto.getDescription());
    }

    private void setEventParameters(Event event, LocalDateTime parse, LocalDateTime parse2, String place, String facebookUrl, String poster, String tldr, String description) {
        event.setStart(parse);
        event.setEnd(parse2);
        event.setPlace(place);
        event.setFacebookUrl(facebookUrl);
        event.setPoster(poster);
        event.setTldr(tldr);
        event.setDescription(description);
    }
}
