package hu.bme.aut.programsch.dto;

import hu.bme.aut.programsch.model.Resort;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDto {
    private String name;
    private CircleDto circle;
    private LocalDateTime start;
    private LocalDateTime end;
    private String place;
    private String facebookUrl;
    private String poster;
    private String tldr;
    private String description;

}
