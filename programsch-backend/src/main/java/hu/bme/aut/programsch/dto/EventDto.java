package hu.bme.aut.programsch.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDto {
    private String eventDescription;
    private CircleDto circle;
    private DayDto day;
    private LocalDateTime start;
    private LocalDateTime finish;
}
