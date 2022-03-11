package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.model.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUserEntity, String> {
}