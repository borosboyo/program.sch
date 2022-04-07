package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DayRepository extends JpaRepository<Day, Long> {

    List<Day> findByDateBetween(LocalDate startOfWeek, LocalDate endOfWeek);
}
