package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.config.RepositoryTestConfig;
import hu.bme.aut.programsch.domain.Resort;
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
class ResortRepositoryTest {
    @Autowired
    private ResortRepository resortRepository;

    @Test
    void testSaveAndFindAll() {
        // given
        int numberOfResortInDatabase = resortRepository.findAll().size();

        Resort resort = new Resort("Test resort");
        resortRepository.save(resort);

        // when
        List<Resort> resortList = resortRepository.findAll();

        // then
        assertEquals(numberOfResortInDatabase + 1, resortList.size());

    }

}
