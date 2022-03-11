package hu.bme.aut.programsch.mapper;

import hu.bme.aut.programsch.dto.SchUserDto;
import hu.bme.aut.programsch.model.SchUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SchUserMapper {
    SchUserDto schUserToDto(SchUser schUser);

    List<SchUserDto> schUsersToDto(List<SchUser> schUsers);
}
