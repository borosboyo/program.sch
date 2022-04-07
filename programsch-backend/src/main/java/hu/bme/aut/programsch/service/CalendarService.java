package hu.bme.aut.programsch.service;


import hu.bme.aut.programsch.model.Calendar;
import hu.bme.aut.programsch.model.Day;
import hu.bme.aut.programsch.repository.CalendarRepository;
import hu.bme.aut.programsch.repository.DayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;

    private final DayRepository dayRepository;

    @Transactional
    public Calendar getCalendar() {
        checkIfCalendarEmpty();
        return calendarRepository.findAll().get(0);
    }


    private void checkIfCalendarEmpty() {
        if (calendarRepository.findAll().isEmpty()) {
            Calendar calendar = new Calendar();
            addOneYear(calendar);
            calendarRepository.save(calendar);
        }
    }

    private void addOneYear(Calendar calendar) {
        for (int ii = 0; ii < 365; ii++) {
            Day day = new Day(LocalDate.now().plusDays(ii));
            day.setCalendar(calendar);
            dayRepository.save(day);
            calendar.getDays().add(day);
        }
    }
}
