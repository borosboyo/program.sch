package hu.bme.aut.programsch.controller.resort;

import hu.bme.aut.programsch.config.WebMvcTestConfig;
import hu.bme.aut.programsch.service.ResortService;
import hu.bme.aut.programsch.web.AppUserController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.mock;

@WebMvcTest(AppUserController.class)
@ContextConfiguration(classes = {WebMvcTestConfig.class, ResortControllerWebMvcTest.ControllerConfig.class})
public class ResortControllerWebMvcTest {

    @TestConfiguration
    static class ControllerConfig {
        @Bean
        public ResortService customerServiceMock() {
            return mock(ResortService.class);
        }
    }
}
