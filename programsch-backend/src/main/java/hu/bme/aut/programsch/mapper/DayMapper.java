package hu.bme.aut.programsch.mapper;

import hu.bme.aut.programsch.dto.DayDto;
import hu.bme.aut.programsch.model.Day;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DayMapper {
    DayDto dayToDto(Day day);

    List<DayDto> daysToDto(List<Day> days);
}
