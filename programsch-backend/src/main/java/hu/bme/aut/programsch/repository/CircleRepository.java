package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.domain.Circle;
import hu.bme.aut.programsch.domain.Resort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CircleRepository extends JpaRepository<Circle, Long> {

    List<Circle> findAllByResort(Resort resort);

    Circle findOneByVirGroupId(long virGroupId);

    Circle findByDisplayName(String displayName);
}
