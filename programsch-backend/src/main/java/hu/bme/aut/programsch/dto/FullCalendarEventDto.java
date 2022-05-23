package hu.bme.aut.programsch.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class FullCalendarEventDto {
    private long id;
    private String title;
    private LocalDate date;
    private boolean allDay = false;
    private LocalDateTime start;
    private LocalDateTime end;
    private ExtendedProps extendedProps;
}
