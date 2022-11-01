package hu.bme.aut.programsch.controller.filter;

import hu.bme.aut.programsch.service.FilterService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import hu.bme.aut.programsch.config.WebMvcTestConfig;
import hu.bme.aut.programsch.web.AppUserController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.mock;

@WebMvcTest(AppUserController.class)
@ContextConfiguration(classes = {WebMvcTestConfig.class, FilterControllerWebMvcTest.ControllerConfig.class})
public class FilterControllerWebMvcTest {

    @TestConfiguration
    static class ControllerConfig {
        @Bean
        public FilterService customerServiceMock() {
            return mock(FilterService.class);
        }
    }
}
