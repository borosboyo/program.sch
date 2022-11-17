package hu.bme.aut.programsch.controller.event;

import hu.bme.aut.programsch.config.WebMvcTestConfig;
import hu.bme.aut.programsch.dto.EventDto;
import hu.bme.aut.programsch.service.EventService;
import hu.bme.aut.programsch.web.AppUserController;
import hu.bme.aut.programsch.web.EventController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//The @WebMvcTest will load all beans required for the controller layer, and we'll get a preconfigured MockMvc environment (dependencies can be mocked).
@WebMvcTest(EventController.class)
@ContextConfiguration(classes = {EventControllerWebMvcTest.ControllerConfig.class})
class EventControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventServiceMock;

    @Test
    void testGettingEvents() throws Exception {
        // given

        List<EventDto> events = List.of(
                new EventDto(),
                new EventDto()
        );

        // when
        when(eventServiceMock.findAll()).thenReturn(events);

        // then
        this.mockMvc.perform(get("/api/event")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

        verify(eventServiceMock, times(2)).findAll();
        verifyNoMoreInteractions(eventServiceMock);
    }

    @TestConfiguration
    static class ControllerConfig {
        @Bean
        public EventService eventServiceMock() {
            return mock(EventService.class);
        }
    }
}
