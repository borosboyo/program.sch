package hu.bme.aut.programsch.controller.event;

import hu.bme.aut.programsch.config.WebMvcTestConfig;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = WebMvcTestConfig.class)
@AutoConfigureMockMvc
public class EventControllerTest {
}