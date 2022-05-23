package hu.bme.aut.programsch.repository;

import hu.bme.aut.programsch.domain.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {

    List<Membership> findByAppUserUid(String appUserUid);
}
