package hu.bme.aut.programsch.controller.appuser;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import hu.bme.aut.programsch.domain.AppUser;
import hu.bme.aut.programsch.dto.AppUserDto;
import hu.bme.aut.programsch.logging.executiontime.GlobalExceptionHandler;
import hu.bme.aut.programsch.mapper.AppUserMapper;
import hu.bme.aut.programsch.service.AppUserService;
import hu.bme.aut.programsch.web.AppUserController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//It's more like a unit test, because we won't load the Spring context but can still test the controller. In this case we have to build and configure
//the environment manually and inject the required mocks as well.
@ExtendWith(MockitoExtension.class)
class AppUserControllerStandaloneWebMvcTest {

    private MockMvc mockMvc;

    @Mock
    private AppUserService appUserServiceMock;

    @Mock
    private AppUserMapper appUserMapperMock;

    @BeforeEach
    public void setUp() {
        AppUserController appUserController = new AppUserController(appUserServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(appUserController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @AfterEach
    public void validate() {
        validateMockitoUsage();
    }

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
