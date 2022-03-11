package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.model.OpeningEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpeningRepository extends JpaRepository<OpeningEntity, Long> {
    List<OpeningEntity> findAllByOrderByDateStart();
    List<OpeningEntity> findAllByDateEndGreaterThanAndDateEndLessThanOrderByDateStart(Long now, Long weekFromNow);
    List<OpeningEntity> findAllByOrderStartGreaterThanAndOrderStartLessThan(Long time1 , Long time2);
    Optional<OpeningEntity> findFirstByCircle_IdOrderByDateStart(Long circle);
    Optional<OpeningEntity> findFirstByCircle_IdAndDateEndGreaterThanOrderByDateStart(Long id, Long time);
    List<OpeningEntity> findAllByOrderStartLessThanAndOrderEndGreaterThan(Long currentTime1, Long currentTime2);
    List<OpeningEntity> findAllByOrderEndGreaterThanOrderByDateStart(Long currentTimeMillis) ;
}
