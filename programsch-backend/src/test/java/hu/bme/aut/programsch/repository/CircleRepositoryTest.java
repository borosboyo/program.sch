package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.config.RepositoryTestConfig;
import hu.bme.aut.programsch.domain.Circle;
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
class CircleRepositoryTest {
    @Autowired
    private CircleRepository circleRepository;

    @Test
    void testSaveAndFindAll() {
        // given
        int numberOfCircleInDatabase = circleRepository.findAll().size();

        Circle circle = new Circle("Test name", null);
        circleRepository.save(circle);

        // when
        List<Circle> circleList = circleRepository.findAll();

        // then
        assertEquals(numberOfCircleInDatabase + 1, circleList.size());

    }

}
