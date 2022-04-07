package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.model.Resort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface ResortRepository extends JpaRepository<Resort, Long> {
    @Modifying
    long removeById(long id);

    Resort findByName(String name);
}
