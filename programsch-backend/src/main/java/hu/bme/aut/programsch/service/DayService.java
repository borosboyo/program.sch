package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.dto.DayDto;
import hu.bme.aut.programsch.mapper.DayMapper;
import hu.bme.aut.programsch.model.Day;
import hu.bme.aut.programsch.repository.DayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DayService {
    private final DayRepository dayRepository;

    private final DayMapper dayMapper;

    @Transactional
    public DayDto findById(long id) {
        Optional<Day> day = dayRepository.findById(id);
        return day.map(dayMapper::dayToDto).orElse(null);
    }

    @Transactional
    public List<DayDto> findAll() {
        return dayMapper.daysToDto(dayRepository.findAll());
    }

    @Transactional
    public DayDto createDay(DayDto dayDto) {
        return new DayDto();
    }

    @Transactional
    public void deleteAll() {
        List<Day> days = dayRepository.findAll();
        for (Day b : days) {
            deleteDay(b.getId());
        }
    }

    @Transactional
    public void deleteDay(long id) {
    }
}
