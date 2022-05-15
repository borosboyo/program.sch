package hu.bme.aut.programsch.dto;

import lombok.Data;

import java.util.List;

@Data
public class CircleDto {
    private String displayName;
    private ResortDto resort;
    private List<EventDto> events;
}
