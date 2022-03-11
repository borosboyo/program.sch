package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.model.CircleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CircleRepository extends JpaRepository<CircleEntity, Long> {
    CircleEntity findByAlias(String alias);

    List<CircleEntity> findAllByAlias(String alias);

    CircleEntity findOneByVirGroupId(long virGroupId);
}
