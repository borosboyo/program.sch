package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.config.RepositoryTestConfig;
import hu.bme.aut.programsch.domain.Filter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Using the @DataJpaTest annotation only entity and repositories will be loaded in the context.
@DataJpaTest
@Import({RepositoryTestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FilterRepositoryTest {
    @Autowired
    private FilterRepository filterRepository;

    @Test
    void testSaveAndFindAll() {
        // given
        int numberOfFilterInDatabase = filterRepository.findAll().size();

        Filter filter = new Filter("Test uid");
        filterRepository.save(filter);

        // when
        List<Filter> filterList = filterRepository.findAll();

        // then
        assertEquals(numberOfFilterInDatabase + 1, filterList.size());

    }

}
