package hu.bme.aut.programsch.mapper;


import hu.bme.aut.programsch.dto.CalendarDto;
import hu.bme.aut.programsch.dto.DayDto;
import hu.bme.aut.programsch.model.Calendar;
import hu.bme.aut.programsch.model.Day;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CalendarMapper {
    CalendarDto calendarToDto(Calendar calendar);

    List<CalendarDto> calendarsToDto(List<Calendar> calendars);

    @Mapping(target = "calendar", ignore = true)
    DayDto dayToDto(Day day);
}
