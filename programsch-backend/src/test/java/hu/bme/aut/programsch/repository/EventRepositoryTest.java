package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.config.RepositoryTestConfig;
import hu.bme.aut.programsch.domain.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Using the @DataJpaTest annotation only entity and repositories will be loaded in the context.
@DataJpaTest
@Import({RepositoryTestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EventRepositoryTest {
    @Autowired
    private EventRepository eventRepository;

    @Test
    void testSaveAndFindAll() {
        // given
        int numberOfEventInDatabase = eventRepository.findAll().size();

        Event event = new Event();
        event.setName("Test event");
        eventRepository.save(event);

        // when
        List<Event> eventList = eventRepository.findAll();

        // then
        assertEquals(numberOfEventInDatabase + 1, eventList.size());

    }

}
