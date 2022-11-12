package hu.bme.aut.programsch.controller.resort;

import hu.bme.aut.programsch.config.WebMvcTestConfig;
import hu.bme.aut.programsch.dto.ResortDto;
import hu.bme.aut.programsch.service.ResortService;
import hu.bme.aut.programsch.web.AppUserController;
import hu.bme.aut.programsch.web.ResortController;
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
@WebMvcTest(ResortController.class)
@ContextConfiguration(classes = {ResortControllerWebMvcTest.ControllerConfig.class})
class ResortControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResortService resortServiceMock;

    @Test
    void testGettingResorts() throws Exception {
        // given

        List<ResortDto> resorts = List.of(
                new ResortDto(),
                new ResortDto()
        );

        // when
        when(resortServiceMock.findAll()).thenReturn(resorts);

        // then
        this.mockMvc.perform(get("/api/resort")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));

        verify(resortServiceMock, times(2)).findAll();
        verifyNoMoreInteractions(resortServiceMock);
    }

    @TestConfiguration
    static class ControllerConfig {
        @Bean
        public ResortService resortServiceMock() {
            return mock(ResortService.class);
        }
    }
}
