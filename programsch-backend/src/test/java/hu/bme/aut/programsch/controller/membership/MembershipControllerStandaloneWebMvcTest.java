package hu.bme.aut.programsch.controller.membership;

import hu.bme.aut.programsch.dto.MembershipDto;
import hu.bme.aut.programsch.logging.executiontime.GlobalExceptionHandler;
import hu.bme.aut.programsch.service.MembershipService;
import hu.bme.aut.programsch.web.MembershipController;
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
class MembershipControllerStandaloneWebMvcTest {

    private MockMvc mockMvc;

    @Mock
    private MembershipService membershipServiceMock;

    @BeforeEach
    public void setUp() {
        MembershipController membershipController = new MembershipController(membershipServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(membershipController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @AfterEach
    public void validate() {
        validateMockitoUsage();
    }

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

}
