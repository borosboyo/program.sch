package hu.bme.aut.programsch.dto;

import lombok.Data;

@Data
public class AppUserDto {
    private String uid;
    private String name;
    private String email;
    private boolean filtersEnabled;
}
