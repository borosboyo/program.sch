package hu.bme.aut.programsch.mapper;

import hu.bme.aut.programsch.domain.Membership;
import hu.bme.aut.programsch.dto.MembershipDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberShipMapper {
    MembershipDto membershipToDto(Membership membership);
    List<MembershipDto> membershipsToDtos(List<Membership> memberships);
}
