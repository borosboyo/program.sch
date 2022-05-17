package hu.bme.aut.programsch.mapper;

import hu.bme.aut.programsch.dto.CircleDto;
import hu.bme.aut.programsch.dto.EventDto;
import hu.bme.aut.programsch.dto.ResortDto;
import hu.bme.aut.programsch.domain.Circle;
import hu.bme.aut.programsch.domain.Event;
import hu.bme.aut.programsch.domain.Resort;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CircleMapper {
    CircleDto circleToDto(Circle circle);

    List<CircleDto> circlesDtos(List<Circle> circle);

    @Mapping(target = "circles", ignore = true)
    ResortDto resortToDto(Resort resort);

    @Mapping(target = "circle", ignore = true)
    EventDto eventToDto(Event events);
}
