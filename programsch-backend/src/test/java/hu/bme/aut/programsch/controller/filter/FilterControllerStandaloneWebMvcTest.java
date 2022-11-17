package hu.bme.aut.programsch.controller.filter;

import java.util.List;

import hu.bme.aut.programsch.domain.AppUser;
import hu.bme.aut.programsch.domain.Filter;
import hu.bme.aut.programsch.dto.AppUserDto;
import hu.bme.aut.programsch.dto.FilterDto;
import hu.bme.aut.programsch.logging.executiontime.GlobalExceptionHandler;
import hu.bme.aut.programsch.service.AppUserService;
import hu.bme.aut.programsch.service.FilterService;
import hu.bme.aut.programsch.web.FilterController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
class FilterControllerStandaloneWebMvcTest {

    private MockMvc mockMvc;

    @Mock
    private FilterService filterServiceMock;

    @Mock
    private AppUserService appUserServiceMock;

    @BeforeEach
    public void setUp() {
        FilterController filterController = new FilterController(filterServiceMock, appUserServiceMock);
        mockMvc = MockMvcBuilders.standaloneSetup(filterController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @AfterEach
    public void validate() {
        validateMockitoUsage();
    }

    @Test
    void testGettingFilters() throws Exception {
        // given
        FilterDto filterDto = new FilterDto();
        filterDto.setUserId("test");
        AppUserDto appUser = new AppUserDto();
        appUser.setUid("test");

        // when
        when(filterServiceMock.findUserFilters("test")).thenReturn(filterDto);
        when(appUserServiceMock.findUser()).thenReturn(appUser);

        // then
        this.mockMvc.perform(get("/api/filter")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));


        verify(filterServiceMock, times(2)).findUserFilters("test");
        verifyNoMoreInteractions(filterServiceMock);
    }
}
