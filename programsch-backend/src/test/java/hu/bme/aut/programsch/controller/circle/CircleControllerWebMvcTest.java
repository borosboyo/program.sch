package hu.bme.aut.programsch.controller.circle;

import hu.bme.aut.programsch.config.WebMvcTestConfig;
import hu.bme.aut.programsch.dto.CircleDto;
import hu.bme.aut.programsch.service.CircleService;
import hu.bme.aut.programsch.web.AppUserController;
import hu.bme.aut.programsch.web.CircleController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//The @WebMvcTest will load all beans required for the controller layer, and we'll get a preconfigured MockMvc environment (dependencies can be mocked).
@WebMvcTest(CircleController.class)
@ContextConfiguration(classes = {CircleControllerWebMvcTest.ControllerConfig.class})
class CircleControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CircleService circleServiceMock;

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
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

        verify(circleServiceMock, times(2)).findAll();
        verifyNoMoreInteractions(circleServiceMock);
    }

    @TestConfiguration
    static class ControllerConfig {
        @Bean
        public CircleService circleServiceMock() {
            return mock(CircleService.class);
        }
    }
}
