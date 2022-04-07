package hu.bme.aut.programsch.dto;

import hu.bme.aut.programsch.model.Resort;
import lombok.Data;

@Data
public class CircleDto {
    private String displayName;
    private ResortDto resort;
}
