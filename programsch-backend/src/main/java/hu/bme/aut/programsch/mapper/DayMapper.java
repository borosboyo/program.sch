package hu.bme.aut.programsch.mapper;

import hu.bme.aut.programsch.dto.CalendarDto;
import hu.bme.aut.programsch.dto.CircleDto;
import hu.bme.aut.programsch.dto.DayDto;
import hu.bme.aut.programsch.dto.EventDto;
import hu.bme.aut.programsch.model.Calendar;
import hu.bme.aut.programsch.model.Circle;
import hu.bme.aut.programsch.model.Day;
import hu.bme.aut.programsch.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DayMapper {

    DayDto dayToDto(Day day);

    List<DayDto> daysToDto(List<Day> days);

    @Mapping(target = "day", ignore = true)
    EventDto eventToDto(Event event);

    @Mapping(target = "days", ignore = true)
    CalendarDto calendarToDto(Calendar calendar);
}
