package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.dto.CalendarDto;
import hu.bme.aut.programsch.mapper.CalendarMapper;
import hu.bme.aut.programsch.model.Calendar;
import hu.bme.aut.programsch.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    private final CalendarMapper calendarMapper;

    @Transactional
    public CalendarDto findById(long id) {
        Optional<Calendar> calendar = calendarRepository.findById(id);
        return calendar.map(calendarMapper::calendarToDto).orElse(null);
    }

    @Transactional
    public List<CalendarDto> findAll() {
        return calendarMapper.calendarsToDto(calendarRepository.findAll());
    }

    @Transactional
    public CalendarDto createCalendar(CalendarDto calendarDto) {
        return new CalendarDto();
    }

    @Transactional
    public void deleteAll() {
        List<Calendar> calendars = calendarRepository.findAll();
        for (Calendar b : calendars) {
            deleteCalendar(b.getId());
        }
    }

    @Transactional
    public void deleteCalendar(long id) {
    }
}
