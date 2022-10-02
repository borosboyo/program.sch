package hu.bme.aut.programsch.dto;

import hu.bme.aut.programsch.domain.AppUser;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Data
public class MembershipDto {
    private String circleName;
    private String role;
    private String appUserUid;

}
