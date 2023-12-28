package hu.bme.aut.programsch.dto;

import lombok.Data;

@Data
public class CreateEventDto {
    private long id;
    private String name;
    private String resort;
    private String circle;
    private String start;
    private String end;
    private String place;
    private String facebookUrl;
    private String poster;
    private String tldr;
    private String description;
}