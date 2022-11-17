package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.config.RepositoryTestConfig;
import hu.bme.aut.programsch.domain.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Using the @DataJpaTest annotation only entity and repositories will be loaded in the context.
@DataJpaTest
@Import({RepositoryTestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AppUserRepositoryTest {
    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    void testSaveAndFindAll() {
        // given
        int numberOfAppUserInDatabase = appUserRepository.findAll().size();

        AppUser appUser1 = new AppUser("Test uid", "Test name", "Test email", "Test room", List.of("Test permission"));
        appUser1.setUid("Test uid1");
        appUserRepository.save(appUser1);

        AppUser appUser2 = new AppUser("Test name", "Test email");
        appUser2.setUid("Test uid2");
        appUserRepository.save(appUser2);

        AppUser appUser3 = new AppUser("Test uid", "Test name", "Test email");
        appUser3.setUid("Test uid3");
        appUserRepository.save(appUser3);

        // when
        List<AppUser> appUserList = appUserRepository.findAll();

        // then
        assertEquals(numberOfAppUserInDatabase + 3, appUserList.size());

    }

}
