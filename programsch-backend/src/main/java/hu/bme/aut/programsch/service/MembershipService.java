package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.config.authsch.response.ProfileDataResponse;
import hu.bme.aut.programsch.domain.Membership;
import hu.bme.aut.programsch.dto.MembershipDto;
import hu.bme.aut.programsch.mapper.MemberShipMapper;
import hu.bme.aut.programsch.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;
    private final MemberShipMapper memberShipMapper;

    @Transactional
    public void addMemberships(ProfileDataResponse profileDataResponses) {
        if(membershipRepository.findByAppUserUid(profileDataResponses.getInternalId().toString()).isEmpty()) {
            for(int ii = 0; ii < profileDataResponses.getEduPersonEntitlements().size(); ii++) {
                Membership membership = new Membership();
                membership.setAppUserUid(profileDataResponses.getInternalId().toString());
                membership.setCircleName(profileDataResponses.getEduPersonEntitlements().get(ii).getName());
                membership.setRole(profileDataResponses.getEduPersonEntitlements().get(ii).getStatus());
                membershipRepository.save(membership);
            }
        }
    }

    @Transactional
    public List<MembershipDto> getMembershipsByAppUserUid(String appUserUid) {
        return memberShipMapper.membershipsToDtos(membershipRepository.findByAppUserUid(appUserUid));
    }
}
