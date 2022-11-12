package hu.bme.aut.programsch.controller.event;

import java.util.List;

import hu.bme.aut.programsch.dto.EventDto;
import hu.bme.aut.programsch.logging.executiontime.GlobalExceptionHandler;
import hu.bme.aut.programsch.service.EventService;
import hu.bme.aut.programsch.web.EventController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//It's more like a unit test, because we won't load the Spring context but can still test the controller. In this case we have to build and configure
//the environment manually and inject the required mocks as well.
@ExtendWith(MockitoExtension.class)
class EventControllerStandaloneWebMvcTest {

    private MockMvc mockMvc;

    @Mock
    private EventService eventServiceMock;

    @BeforeEach
    public void setUp() {
        EventController eventController = new EventController(eventServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(eventController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @AfterEach
    public void validate() {
        validateMockitoUsage();
    }

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
                .andExpect(content().contentType("application/json"));


        verify(eventServiceMock, times(2)).findAll();
        verifyNoMoreInteractions(eventServiceMock);
    }
}
