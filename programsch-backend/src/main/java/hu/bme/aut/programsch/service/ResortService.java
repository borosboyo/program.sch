package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.dto.ResortDto;
import hu.bme.aut.programsch.mapper.ResortMapper;
import hu.bme.aut.programsch.model.Resort;
import hu.bme.aut.programsch.repository.ResortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResortService {
    private final ResortRepository resortRepository;

    private final ResortMapper resortMapper;

    @Transactional
    public ResortDto findById(long id) {
        Optional<Resort> resort = resortRepository.findById(id);
        return resort.map(resortMapper::resortToDto).orElse(null);
    }

    @Transactional
    public List<ResortDto> findAll() {
        return resortMapper.resortsToDto(resortRepository.findAll());
    }

    @Transactional
    public ResortDto createResort(ResortDto resortDto) {
        return new ResortDto();
    }

    @Transactional
    public void deleteAll() {
        List<Resort> resorts = resortRepository.findAll();
        for (Resort b : resorts) {
            deleteResort(b.getId());
        }
    }

    @Transactional
    public void deleteResort(long id) {
    }

    public ResortDto updateResort(ResortDto resortDto) {
        return resortDto;
    }
}
