package hu.bme.aut.programsch.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DayDto {
    private LocalDate date;
    private List<EventDto> events;
    private CalendarDto calendar;
}
