package hu.bme.aut.programsch.service;


import java.util.List;

import hu.bme.aut.programsch.domain.Event;
import hu.bme.aut.programsch.dto.CreateEventDto;
import hu.bme.aut.programsch.dto.EventDto;
import hu.bme.aut.programsch.mapper.EventMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EventServiceTestIT {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventMapper eventMapper;

    @Test
    void testGettingEvent() {
        // given, when
        List<EventDto> eventDtos = eventService.findAll();

        // then
        assertTrue(eventDtos.size() > 0);
        assertNotNull(eventService.findAll());
    }

    @Test
    void testGettingEventById() {
        // given
        CreateEventDto event = new CreateEventDto();
        event.setCircle("KB PR Csoport");
        event.setResort("Egyéb");
        event.setStart("2021-05-01 12:00");
        event.setEnd("2021-05-01 13:00");
        event.setName("Teszt esemény");
        event.setPlace("Teszt helyszín");
        event.setDescription("Teszt részletek");

        // when
        EventDto savedEvent = eventService.createEvent(event);

        // then
        assertNotNull(savedEvent);
        assertNotNull(eventService.findById(savedEvent.getId()));
    }

    @Test
    void testGettingEventByDay() {
        // given
        CreateEventDto event = new CreateEventDto();
        event.setCircle("KB PR Csoport");
        event.setResort("Egyéb");
        event.setStart("2021-05-01 12:00");
        event.setEnd("2021-05-01 13:00");
        event.setName("Teszt esemény");
        event.setPlace("Teszt helyszín");
        event.setDescription("Teszt részletek");

        // when
        EventDto savedEvent = eventService.createEvent(event);

        // then
        assertNotNull(savedEvent);
        assertNotNull(eventService.findEventsByDay("2021-05-01"));
    }

    @Test
    void testUpdateEvent() {
        // given
        CreateEventDto event = new CreateEventDto();
        event.setCircle("KB PR Csoport");
        event.setResort("Egyéb");
        event.setStart("2021-05-01 12:00");
        event.setEnd("2021-05-01 13:00");
        event.setName("Teszt esemény");
        event.setPlace("Teszt helyszín");
        event.setDescription("Teszt részletek");
        EventDto temp = eventService.createEvent(event);

        // when
        CreateEventDto savedEvent = new CreateEventDto();
        savedEvent.setCircle("KB PR Csoport");
        savedEvent.setResort("Egyéb");
        savedEvent.setStart("2021-05-01 12:00");
        savedEvent.setEnd("2021-05-01 13:00");
        savedEvent.setName("Teszt esemény 2");
        savedEvent.setPlace("Teszt helyszín");
        savedEvent.setDescription("Teszt részletek");
        savedEvent.setId(temp.getId());
        EventDto updatedEvent = eventService.updateEvent(savedEvent);

        // then
        assertNotNull(updatedEvent);
        assertEquals("Teszt esemény 2", updatedEvent.getName());
    }

    @Test
    void testSavingEvent() {
        // given
        CreateEventDto event = new CreateEventDto();
        event.setCircle("KB PR Csoport");
        event.setResort("Egyéb");
        event.setStart("2021-05-01 12:00");
        event.setEnd("2021-05-01 13:00");
        event.setName("Teszt esemény");
        event.setPlace("Teszt helyszín");
        event.setDescription("Teszt részletek");

        // when
        EventDto savedEvent = eventService.createEvent(event);

        // then
        assertNotNull(savedEvent);
    }


    @Test
    void testDeleteEvent() {
        // given
        CreateEventDto event = new CreateEventDto();
        event.setCircle("KB PR Csoport");
        event.setResort("Egyéb");
        event.setStart("2021-05-01 12:00");
        event.setEnd("2021-05-01 13:00");
        event.setName("Teszt esemény");
        event.setPlace("Teszt helyszín");
        event.setDescription("Teszt részletek");
        event.setId(1L);
        EventDto temp = eventService.createEvent(event);

        // when
        eventService.findById(temp.getId());
        eventService.deleteEvent(temp.getId());

        // then
        assertNull(eventService.findById(temp.getId()));
    }

}
