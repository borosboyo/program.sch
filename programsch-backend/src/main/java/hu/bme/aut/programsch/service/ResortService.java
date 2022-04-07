package hu.bme.aut.programsch.service;


import hu.bme.aut.programsch.dto.ResortDto;
import hu.bme.aut.programsch.mapper.ResortMapper;
import hu.bme.aut.programsch.model.Resort;
import hu.bme.aut.programsch.repository.ResortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResortService {
    private final ResortRepository resortRepository;

    private final ResortMapper resortMapper;

    @Transactional
    public List<ResortDto> findAll() {
        return resortMapper.resortsToDtos(resortRepository.findAll());
    }
}
