package hu.bme.aut.programsch.service;


import java.util.List;
import java.util.UUID;

import hu.bme.aut.programsch.config.authsch.response.ProfileDataResponse;
import hu.bme.aut.programsch.domain.Membership;
import hu.bme.aut.programsch.dto.MembershipDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MembershipServiceTestIT {

    @Autowired
    private MembershipService membershipService;

    @Test
    void testMemberships() {
        // given
        ProfileDataResponse.ProfileDataResponseBuilder profileDataResponsesBuilder = ProfileDataResponse.newBuilder();
        profileDataResponsesBuilder.setInternalId(UUID.fromString("138a015b-ec64-d852-2b0a-20afebc453e9"));

        ProfileDataResponse profileDataResponses = profileDataResponsesBuilder.build();

        // when
        membershipService.addMemberships(profileDataResponses);


        // then
        assertEquals(0, membershipService.getMembershipsByAppUserUid("138a015b-ec64-d852-2b0a-20afebc453e9").size());
        assertEquals(5, membershipService.getMembershipsOfUser().size());
    }
}
