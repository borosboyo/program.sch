package hu.bme.aut.programsch.mapper;

import hu.bme.aut.programsch.domain.Membership;
import hu.bme.aut.programsch.dto.AppUserDto;
import hu.bme.aut.programsch.domain.AppUser;
import hu.bme.aut.programsch.dto.MembershipDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppUserMapper {
    AppUserDto appUserToDto(AppUser appUser);

    AppUser dtoToAppUser(AppUserDto appUserDto);

    List<AppUserDto> appUserToDto(List<AppUser> appUserList);

    List<AppUser> dtoToAppUser(List<AppUserDto> appUserDtoList);

    List<AppUserDto> appUsersToDtos(List<AppUser> appUsers);
}
