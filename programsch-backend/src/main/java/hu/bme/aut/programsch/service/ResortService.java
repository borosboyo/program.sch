package hu.bme.aut.programsch.service;


import hu.bme.aut.programsch.model.Resort;
import hu.bme.aut.programsch.repository.ResortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResortService {
    private final ResortRepository resortRepository;

    public List<Resort> findAll() {
        return resortRepository.findAll();
    }
}
