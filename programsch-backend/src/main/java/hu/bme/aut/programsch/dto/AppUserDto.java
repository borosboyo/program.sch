package hu.bme.aut.programsch.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AppUserDto {
    private String uid;
    private String name;
    private String email;
    private List<CircleFilterDto> filters = new ArrayList<>();
    private boolean filtersEnabled = false;
}
