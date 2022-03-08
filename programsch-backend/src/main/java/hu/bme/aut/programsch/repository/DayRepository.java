package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {
    @Modifying
    long removeById(long id);

    List<Day> findByName(String name);
}