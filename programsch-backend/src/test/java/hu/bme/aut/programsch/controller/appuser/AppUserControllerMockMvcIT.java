package hu.bme.aut.programsch.controller.appuser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//Controller layer test with full context and without mocking
//Dependencies are not mocked and a test method/test call can go through all layers.
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class AppUserControllerMockMvcIT {

    private static final String BASE_URL = "/api/appuser";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGettingAppUser() throws Exception {
        // when, then
        this.mockMvc.perform(get(BASE_URL)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

        MvcResult result = this.mockMvc.perform(get(BASE_URL).content("application/json")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("\"email\":\"borosgergo00@gmail.com\""));
    }

}
