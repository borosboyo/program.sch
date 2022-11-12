package hu.bme.aut.programsch.controller.event;

import hu.bme.aut.programsch.config.WebMvcTestConfig;
import java.util.List;

import hu.bme.aut.programsch.dto.EventDto;
import hu.bme.aut.programsch.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//It's still a MockMvc based environment but with full Spring context by using @SpringBootTest and @AutoConfigureMockMvc annotations.
@SpringBootTest(classes = WebMvcTestConfig.class)
@AutoConfigureMockMvc
class EventControllerTest {

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

}
