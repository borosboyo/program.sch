package hu.bme.aut.programsch.controller.resort;

import hu.bme.aut.programsch.dto.ResortDto;
import hu.bme.aut.programsch.logging.executiontime.GlobalExceptionHandler;
import hu.bme.aut.programsch.service.ResortService;
import hu.bme.aut.programsch.web.ResortController;
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
class ResortControllerStandaloneWebMvcTest {

    private MockMvc mockMvc;

    @Mock
    private ResortService resortServiceMock;

    @BeforeEach
    public void setUp() {
        ResortController resortController = new ResortController(resortServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(resortController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @AfterEach
    public void validate() {
        validateMockitoUsage();
    }

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
                .andExpect(content().contentType("application/json"));


        verify(resortServiceMock, times(2)).findAll();
        verifyNoMoreInteractions(resortServiceMock);
    }

}
