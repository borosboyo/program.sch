package hu.bme.aut.programsch.mapper;


import hu.bme.aut.programsch.dto.CircleFilterDto;
import hu.bme.aut.programsch.model.Circle;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CircleMapper {

    CircleFilterDto circleToCircleFilterDto(Circle circle);

    List<CircleFilterDto> circlesToCircleFilterDtos(List<Circle> circles);

}
