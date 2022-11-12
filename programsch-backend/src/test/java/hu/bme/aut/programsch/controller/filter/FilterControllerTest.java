package hu.bme.aut.programsch.controller.filter;

import hu.bme.aut.programsch.config.WebMvcTestConfig;

import hu.bme.aut.programsch.dto.AppUserDto;
import hu.bme.aut.programsch.dto.FilterDto;
import hu.bme.aut.programsch.service.AppUserService;
import hu.bme.aut.programsch.service.FilterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//It's still a MockMvc based environment but with full Spring context by using @SpringBootTest and @AutoConfigureMockMvc annotations.
@SpringBootTest(classes = WebMvcTestConfig.class)
@AutoConfigureMockMvc
class FilterControllerTest {

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
        AppUserDto appUser = new AppUserDto();
        appUser.setUid("test");

        // when
        when(appUserServiceMock.findUser()).thenReturn(appUser);
        when(filterServiceMock.findUserFilters(appUserServiceMock.findUser().getUid())).thenReturn(filterDto);

        System.out.println(filterServiceMock.findUserFilters("test"));
        System.out.println(appUserServiceMock.findUser());

        // then
        this.mockMvc.perform(get("/api/filter")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());


        verify(filterServiceMock, times(1)).findUserFilters("test");
    }

}
