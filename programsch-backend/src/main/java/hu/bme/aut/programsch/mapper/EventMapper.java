package hu.bme.aut.programsch.mapper;

import hu.bme.aut.programsch.dto.DayDto;
import hu.bme.aut.programsch.dto.EventDto;
import hu.bme.aut.programsch.dto.ResortDto;
import hu.bme.aut.programsch.model.Day;
import hu.bme.aut.programsch.model.Event;
import hu.bme.aut.programsch.model.Resort;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDto eventToDto(Event event);
    List<EventDto> eventsToDtos(List<Event> event);

    @Mapping(target = "events", ignore = true)
    DayDto dayToDto(Day day);
}
