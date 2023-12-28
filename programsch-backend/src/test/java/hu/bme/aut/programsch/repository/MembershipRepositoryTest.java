package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.config.RepositoryTestConfig;
import hu.bme.aut.programsch.domain.Membership;
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
class MembershipRepositoryTest {
    @Autowired
    private MembershipRepository membershipRepository;

    @Test
    void testSaveAndFindAll() {
        // given
        int numberOfMembershipInDatabase = membershipRepository.findAll().size();

        Membership membership = new Membership("Test uid", "Test name", "Test status");
        membership.setCircleName("Test circle");
        membershipRepository.save(membership);

        // when
        List<Membership> membershipList = membershipRepository.findAll();

        // then
        assertEquals(numberOfMembershipInDatabase + 1, membershipList.size());

    }

}
