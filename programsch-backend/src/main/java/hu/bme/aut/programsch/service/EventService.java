package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.model.Event;
import hu.bme.aut.programsch.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository openingRepository;

    @Transactional
    public List<Event> findAll() {
        return openingRepository.findAll();
    }

    @Transactional
    public void save(Event openingEntity) {
        openingRepository.save(openingEntity);
    }
}
