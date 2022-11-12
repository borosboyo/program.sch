package hu.bme.aut.programsch.controller.appUser;

import hu.bme.aut.programsch.config.WebMvcTestConfig;
import java.util.List;

import hu.bme.aut.programsch.dto.AppUserDto;
import hu.bme.aut.programsch.dto.AppUserDto;
import hu.bme.aut.programsch.service.AppUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserService appUserServiceMock;

    @Test
    void testGettingAppUsers() throws Exception {
        // given
        AppUserDto appUserDto = new AppUserDto();
        appUserDto.setEmail("kutya@test.com");

        // when
        when(appUserServiceMock.findUser()).thenReturn(appUserDto);

        // then
        this.mockMvc.perform(get("/api/appuser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

        MvcResult result = this.mockMvc.perform(get("/api/appuser").content("application/json")).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("\"email\":\"kutya@test.com\""));

        verify(appUserServiceMock, times(4)).findUser();
        verifyNoMoreInteractions(appUserServiceMock);
    }

}
