package hu.bme.aut.programsch.controller.appuser;

import hu.bme.aut.programsch.config.WebMvcTestConfig;
import hu.bme.aut.programsch.dto.AppUserDto;
import hu.bme.aut.programsch.service.AppUserService;
import hu.bme.aut.programsch.web.AppUserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppUserController.class)
@ContextConfiguration(classes = {AppUserControllerWebMvcTest.ControllerConfig.class})
class AppUserControllerWebMvcTest {

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

    @TestConfiguration
    static class ControllerConfig {
        @Bean
        public AppUserService appUserServiceMock() {
            return mock(AppUserService.class);
        }
    }
}
