package hu.bme.aut.programsch.controller.circle;

import hu.bme.aut.programsch.config.WebMvcTestConfig;
import java.util.List;

import hu.bme.aut.programsch.dto.CircleDto;
import hu.bme.aut.programsch.service.CircleService;
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
class CircleControllerTest {

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

}
