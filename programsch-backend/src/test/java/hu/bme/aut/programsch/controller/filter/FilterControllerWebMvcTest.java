package hu.bme.aut.programsch.controller.filter;

import hu.bme.aut.programsch.dto.AppUserDto;
import hu.bme.aut.programsch.dto.FilterDto;
import hu.bme.aut.programsch.service.AppUserService;
import hu.bme.aut.programsch.service.FilterService;
import hu.bme.aut.programsch.web.FilterController;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//The @WebMvcTest will load all beans required for the controller layer, and we'll get a preconfigured MockMvc environment (dependencies can be mocked).
@WebMvcTest(FilterController.class)
@ContextConfiguration(classes = {FilterControllerWebMvcTest.ControllerConfig.class, FilterController.class})
class FilterControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilterService filterServiceMock;

    @Mock
    private AppUserService appUserServiceMock;

    @Test
    void testGettingFilters() throws Exception {
        // given
        FilterDto filterDto = new FilterDto();
        filterDto.setUserId("test");
        filterDto.setFilteredCircles(new ArrayList<>());
        AppUserDto appUser = new AppUserDto();
        appUser.setUid("test");

        // when
        when(appUserServiceMock.findUser()).thenReturn(new AppUserDto());
        when(filterServiceMock.findUserFilters("test")).thenReturn(filterDto);
        // then
        this.mockMvc.perform(get("/api/filter")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
    }

    @TestConfiguration
    static class ControllerConfig {
        @Bean
        public FilterService filterServiceMock() {
            return mock(FilterService.class);
        }

        @Bean
        public AppUserService appUserServiceMock() {
            return mock(AppUserService.class);
        }
    }
}
