package hu.bme.aut.programsch.controller.membership;

import hu.bme.aut.programsch.config.WebMvcTestConfig;
import hu.bme.aut.programsch.service.MembershipService;
import hu.bme.aut.programsch.web.AppUserController;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.mock;

@WebMvcTest(AppUserController.class)
@ContextConfiguration(classes = {WebMvcTestConfig.class, MembershipControllerWebMvcTest.ControllerConfig.class})
public class MembershipControllerWebMvcTest {

    @TestConfiguration
    static class ControllerConfig {
        @Bean
        public MembershipService customerServiceMock() {
            return mock(MembershipService.class);
        }
    }
}
