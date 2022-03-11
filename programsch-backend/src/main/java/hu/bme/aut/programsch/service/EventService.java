package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.dto.EventDto;
import hu.bme.aut.programsch.mapper.EventMapper;
import hu.bme.aut.programsch.model.Event;
import hu.bme.aut.programsch.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    @Transactional
    public EventDto findById(long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.map(eventMapper::eventToDto).orElse(null);
    }

    @Transactional
    public List<EventDto> findAll() {
        return eventMapper.eventsToDto(eventRepository.findAll());
    }

    @Transactional
    public EventDto createEvent(EventDto eventDto) {
        return new EventDto();
    }

    @Transactional
    public void deleteAll() {
        List<Event> events = eventRepository.findAll();
        for (Event b : events) {
            deleteEvent(b.getId());
        }
    }

    @Transactional
    public void deleteEvent(long id) {
    }

    public EventDto updateEvent(EventDto eventDto) {
        return eventDto;
    }
}
