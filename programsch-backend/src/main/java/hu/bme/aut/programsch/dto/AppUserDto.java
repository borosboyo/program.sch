package hu.bme.aut.programsch.dto;

import hu.bme.aut.programsch.config.authsch.struct.PersonEntitlement;
import hu.bme.aut.programsch.domain.Membership;
import lombok.Data;

import java.util.List;

@Data
public class AppUserDto {
    private String uid;
    private String name;
    private String email;
    private boolean filtersEnabled;
}
