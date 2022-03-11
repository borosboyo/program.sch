package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Modifying
    long removeById(long id);

    List<Event> findByName(String name);
}
