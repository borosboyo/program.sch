package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.model.OpeningEntity;
import hu.bme.aut.programsch.repository.OpeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpeningService {

    private final OpeningRepository openingRepository;

    @Transactional
    public List<OpeningEntity> findAll(){
        return openingRepository.findAllByOrderByDateStart();
    }

    @Transactional
    public void save(OpeningEntity openingEntity){
        openingRepository.save(openingEntity);
    }
}
