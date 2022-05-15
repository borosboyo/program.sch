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

}
