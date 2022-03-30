package hu.bme.aut.programsch.mapper;

import hu.bme.aut.programsch.dto.AppUserDto;
import hu.bme.aut.programsch.model.AppUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
    AppUserDto appUserEntityToDto(AppUser appUser);

    List<AppUserDto> appUserEntitiesToDtos(List<AppUser> appUsers);


}
