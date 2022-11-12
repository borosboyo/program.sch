package hu.bme.aut.programsch.controller.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Controller layer test with full context and without mocking
//Dependencies are not mocked and a test method/test call can go through all layers.
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class FilterControllerMockMvcIT {

    private static final String BASE_URL = "/api/filter";

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testGetFilterState() throws Exception {
        // when, then
        this.mockMvc.perform(get(BASE_URL + "/filtersEnabled")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        MvcResult result = this.mockMvc.perform(get(BASE_URL).content("application/json")).andExpect(status().isOk()).andReturn();
        assertFalse(result.getResponse().getContentAsString().isEmpty());

    }

    @Test
    void testEnableFilters() throws Exception {
        // when, then
        this.mockMvc.perform(post(BASE_URL + "/enableFilters")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void testDisableFilters() throws Exception {
        // when, then
        this.mockMvc.perform(post(BASE_URL + "/disableFilters")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }
}
