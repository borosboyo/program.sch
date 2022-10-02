package hu.bme.aut.programsch.mapper;

import hu.bme.aut.programsch.domain.Resort;
import hu.bme.aut.programsch.dto.CircleDto;
import hu.bme.aut.programsch.dto.EventDto;
import hu.bme.aut.programsch.domain.Circle;
import hu.bme.aut.programsch.domain.Event;
import hu.bme.aut.programsch.dto.ResortDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDto eventToDto(Event event);

    List<EventDto> eventsToDtos(List<Event> event);

    @Mapping(target = "events", ignore = true)
    @Mapping(target = "resort", ignore = true)
    CircleDto circleToDto(Circle circle);
}
