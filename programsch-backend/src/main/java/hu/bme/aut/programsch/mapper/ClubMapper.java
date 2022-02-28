package hu.bme.aut.programsch.mapper;

import hu.bme.aut.programsch.dto.ClubDto;
import hu.bme.aut.programsch.model.Club;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClubMapper {
    ClubDto clubToDto(Club club);

    List<ClubDto> clubsToDto(List<Club> clubs);
}
