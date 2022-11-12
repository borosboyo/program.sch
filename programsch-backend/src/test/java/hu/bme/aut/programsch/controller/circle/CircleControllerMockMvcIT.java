package hu.bme.aut.programsch.controller.circle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Controller layer test with full context and without mocking
//Dependencies are not mocked and a test method/test call can go through all layers.
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class CircleControllerMockMvcIT {


    private static final String BASE_URL = "/api/circle";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGettingCircles() throws Exception {
        // when, then
        this.mockMvc.perform(get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(61)))
                .andExpect(jsonPath("$[0].displayName", is("Dezső Buli")));
    }

}
