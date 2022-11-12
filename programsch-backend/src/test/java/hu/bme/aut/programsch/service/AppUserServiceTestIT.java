package hu.bme.aut.programsch.service;


import java.util.List;

import hu.bme.aut.programsch.domain.AppUser;
import hu.bme.aut.programsch.dto.AppUserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AppUserServiceTestIT {

    @Autowired
    private AppUserService appUserService;

    @Test
    void testGettingAppUser() {
        // given, when
        List<AppUserDto> appUserDtos = appUserService.findAll();

        // then
        assertEquals(1, appUserDtos.size());
        assertNotNull(appUserService.findUser());

    }

    @Test
    void testSavingAppUser() {
        // given
        AppUser appuser = new AppUser("test", "test", "test");

        // when
        AppUserDto savedCustomer = appUserService.save(appuser);

        // then
        assertNotNull(savedCustomer);
        assertTrue(appUserService.exists(savedCustomer.getUid()));
    }

}
