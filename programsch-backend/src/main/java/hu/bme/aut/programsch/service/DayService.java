package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.model.Day;
import hu.bme.aut.programsch.repository.DayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DayService {
    private final DayRepository dayRepository;

    @Transactional
    public List<Day> getWeek(LocalDate startOfWeek, LocalDate endOfWeek) {
        return dayRepository.findByDateBetween(startOfWeek, endOfWeek);
    }
}
