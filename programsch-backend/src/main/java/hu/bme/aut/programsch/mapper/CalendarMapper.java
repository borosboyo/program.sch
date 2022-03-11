package hu.bme.aut.programsch.mapper;

import hu.bme.aut.programsch.dto.CalendarDto;
import hu.bme.aut.programsch.model.Calendar;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CalendarMapper {
    CalendarDto calendarToDto(Calendar calendar);

    List<CalendarDto> calendarsToDto(List<Calendar> calendars);
}
