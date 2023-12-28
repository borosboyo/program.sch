package hu.bme.aut.programsch.controller.membership;

import hu.bme.aut.programsch.config.WebMvcTestConfig;
import java.util.List;

import hu.bme.aut.programsch.dto.MembershipDto;
import hu.bme.aut.programsch.service.MembershipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//It's still a MockMvc based environment but with full Spring context by using @SpringBootTest and @AutoConfigureMockMvc annotations.
@SpringBootTest(classes = WebMvcTestConfig.class)
@AutoConfigureMockMvc
class MembershipControllerTest {

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

}
