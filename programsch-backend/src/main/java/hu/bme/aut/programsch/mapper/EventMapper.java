package hu.bme.aut.programsch.mapper;

import hu.bme.aut.programsch.dto.EventDto;
import hu.bme.aut.programsch.model.Event;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventDto eventToDto(Event event);

    List<EventDto> eventsToDto(List<Event> events);
}
