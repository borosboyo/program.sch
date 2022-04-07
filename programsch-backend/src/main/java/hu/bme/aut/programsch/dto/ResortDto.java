package hu.bme.aut.programsch.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResortDto {
    private String name;
    private List<CircleDto> circles;
}
