package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.config.RepositoryTestConfig;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({RepositoryTestConfig.class})
public class FilterRepositoryTest {
}
