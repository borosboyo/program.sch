package hu.bme.aut.programsch.controller.circle;

import hu.bme.aut.programsch.dto.CircleDto;
import hu.bme.aut.programsch.logging.executiontime.GlobalExceptionHandler;
import hu.bme.aut.programsch.service.CircleService;
import hu.bme.aut.programsch.web.CircleController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//It's more like a unit test, because we won't load the Spring context but can still test the controller. In this case we have to build and configure
//the environment manually and inject the required mocks as well.
@ExtendWith(MockitoExtension.class)
class CircleControllerStandaloneWebMvcTest {

    private MockMvc mockMvc;

    @Mock
    private CircleService circleServiceMock;

    @BeforeEach
    public void setUp() {
        CircleController circleController = new CircleController(circleServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(circleController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @AfterEach
    public void validate() {
        validateMockitoUsage();
    }

    @Test
    void testGettingCircles() throws Exception {
        // given
        List<CircleDto> circles = List.of(
                new CircleDto(),
                new CircleDto()
        );

        // when
        when(circleServiceMock.findAll()).thenReturn(circles);

        // then
        this.mockMvc.perform(get("/api/circle")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));


        verify(circleServiceMock, times(2)).findAll();
        verifyNoMoreInteractions(circleServiceMock);
    }

}
