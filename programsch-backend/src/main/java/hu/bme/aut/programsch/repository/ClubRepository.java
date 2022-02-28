package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    @Modifying
    long removeById(long id);

    List<Club> findByName(String name);
}
