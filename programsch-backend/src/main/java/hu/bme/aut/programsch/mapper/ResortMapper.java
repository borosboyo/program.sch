package hu.bme.aut.programsch.mapper;

import hu.bme.aut.programsch.dto.ResortDto;
import hu.bme.aut.programsch.model.Resort;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResortMapper {
    ResortDto resortToDto(Resort resort);

    List<hu.bme.aut.programsch.dto.ResortDto> resortsToDto(List<Resort> resorts);
}
