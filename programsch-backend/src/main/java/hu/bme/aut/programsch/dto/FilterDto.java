package hu.bme.aut.programsch.dto;

import lombok.Data;

import java.util.List;

@Data
public class FilterDto {
    private long id;
    private String userId;
    private List<String> filteredCircles;
}
