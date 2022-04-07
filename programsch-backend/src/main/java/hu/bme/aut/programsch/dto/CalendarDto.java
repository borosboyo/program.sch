package hu.bme.aut.programsch.dto;

import lombok.Data;

import java.util.List;

@Data
public class CalendarDto {
    private List<DayDto> days;
}
