package hu.bme.aut.programsch.controller.event;

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
class EventControllerMockMvcIT {


    private static final String BASE_URL = "/api/event";
    private static final String GET_BY_DATE_URL = BASE_URL + "/day";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGettingEvents() throws Exception {
        // when, then
        this.mockMvc.perform(get(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        MvcResult result = this.mockMvc.perform(get(BASE_URL).content("application/json")).andExpect(status().isOk()).andReturn();
        assertFalse(result.getResponse().getContentAsString().isEmpty());

    }

    @Test
    void testCreateEvent() throws Exception {
        String createEventJson = "{\n" +
                "  \"name\": \"Test Event\",\n" +
                "  \"start\": \"2021-05-01T10:00\",\n" +
                "  \"end\": \"2021-05-01T11:00\",\n" +
                "  \"circle\": \"Schörpong\",\n" +
                "  \"resort\": \"Bulis Reszort\",\n" +
                "  \"facebookUrl\": \"Hehe\",\n" +
                "  \"poster\": \"Hehe\",\n" +
                "  \"description\": \"Test Description\",\n" +
                "  \"place\": \"Test Location\",\n" +
                "  \"tldr\": \"hehe\"\n" +
                "}";
        //when,then
        this.mockMvc.perform(post(BASE_URL).content(createEventJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        MvcResult result = this.mockMvc.perform(get(BASE_URL).content("application/json")).andExpect(status().isOk()).andReturn();
        assertFalse(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    void testGettingEventsByDay () throws Exception {

        // when
        String event = "{\n" +
                "  \"name\": \"Test Event\",\n" +
                "  \"start\": \"2022-10-25T10:00\",\n" +
                "  \"end\": \"2022-10-25T11:00\",\n" +
                "  \"circle\": \"Schörpong\",\n" +
                "  \"resort\": \"Bulis Reszort\",\n" +
                "  \"facebookUrl\": \"Hehe\",\n" +
                "  \"poster\": \"Hehe\",\n" +
                "  \"description\": \"Test Description\",\n" +
                "  \"place\": \"Test Location\",\n" +
                "  \"tldr\": \"hehe\"\n" +
                "}";
        this.mockMvc.perform(post(BASE_URL).content(event)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        //then
        this.mockMvc.perform(get(GET_BY_DATE_URL).param("date", "10-25")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testDeleteEventById() throws Exception {
        //when
        String createEventJson = "{\n" +
                "  \"name\": \"Test Event\",\n" +
                "  \"start\": \"2021-05-01T10:00\",\n" +
                "  \"end\": \"2021-05-01T11:00\",\n" +
                "  \"circle\": \"Schörpong\",\n" +
                "  \"resort\": \"Bulis Reszort\",\n" +
                "  \"facebookUrl\": \"Hehe\",\n" +
                "  \"poster\": \"Hehe\",\n" +
                "  \"description\": \"Test Description\",\n" +
                "  \"place\": \"Test Location\",\n" +
                "  \"tldr\": \"hehe\"\n" +
                "}";

        this.mockMvc.perform(post(BASE_URL).content(createEventJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        MvcResult result = this.mockMvc.perform(get(BASE_URL).content("application/json")).andExpect(status().isOk()).andReturn();
        assertFalse(result.getResponse().getContentAsString().isEmpty());

        //then
        this.mockMvc.perform(delete("/api/event/{id}", 101)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

}