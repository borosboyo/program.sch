package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    @Modifying
    long removeById(long id);

    List<Calendar> findByName(String name);
}
