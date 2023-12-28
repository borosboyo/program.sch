package hu.bme.aut.programsch.controller.membership;

import hu.bme.aut.programsch.config.WebMvcTestConfig;
import hu.bme.aut.programsch.dto.MembershipDto;
import hu.bme.aut.programsch.service.MembershipService;
import hu.bme.aut.programsch.web.AppUserController;
import hu.bme.aut.programsch.web.MembershipController;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//The @WebMvcTest will load all beans required for the controller layer, and we'll get a preconfigured MockMvc environment (dependencies can be mocked).
@WebMvcTest(MembershipController.class)
@ContextConfiguration(classes = {MembershipControllerWebMvcTest.ControllerConfig.class})
class MembershipControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MembershipService membershipServiceMock;

    @Test
    void testGettingMemberships() throws Exception {
        // given
        List<MembershipDto> memberships = List.of(
                new MembershipDto(),
                new MembershipDto()
        );

        // when
        when(membershipServiceMock.getMembershipsOfUser()).thenReturn(memberships);

        // then
        this.mockMvc.perform(get("/api/membership")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));


        verify(membershipServiceMock, times(2)).getMembershipsOfUser();
        verifyNoMoreInteractions(membershipServiceMock);
    }

    @TestConfiguration
    static class ControllerConfig {
        @Bean
        public MembershipService membershipServiceMock() {
            return mock(MembershipService.class);
        }
    }
}
