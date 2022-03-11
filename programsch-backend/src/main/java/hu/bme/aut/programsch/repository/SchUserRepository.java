package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.model.SchUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchUserRepository extends JpaRepository<SchUser, Long> {
    @Modifying
    long removeById(long id);

    List<SchUser> findByName(String name);
}
