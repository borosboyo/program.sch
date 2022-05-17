package hu.bme.aut.programsch.mapper;

import hu.bme.aut.programsch.dto.CircleDto;
import hu.bme.aut.programsch.dto.ResortDto;
import hu.bme.aut.programsch.domain.Circle;
import hu.bme.aut.programsch.domain.Resort;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResortMapper {
    ResortDto resortToDto(Resort resort);

    List<ResortDto> resortsToDtos(List<Resort> resorts);

    @Mapping(target = "resort", ignore = true)
    @Mapping(target = "events", ignore = true)
    CircleDto circleToDto(Circle Circle);
}
