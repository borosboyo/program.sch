package hu.bme.aut.programsch.mapper;

import hu.bme.aut.programsch.dto.FullCalendarEventDto;
import hu.bme.aut.programsch.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FullCalendarEventDtoMapper {
    @Mapping(target = "id", source = "event.id")
    @Mapping(target = "title", source = "event.name")
    FullCalendarEventDto eventToDto(Event event);

    List<FullCalendarEventDto> eventsToDtos(List<Event> event);
}
