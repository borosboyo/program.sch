package hu.bme.aut.programsch.mapper;

import hu.bme.aut.programsch.domain.Filter;
import hu.bme.aut.programsch.dto.FilterDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FilterMapper {
    FilterDto filterToDto(Filter filter);

    List<FilterDto> filtersToDtos(List<Filter> filter);
}
