package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.model.CircleEntity;
import hu.bme.aut.programsch.repository.CircleRepository;
import hu.bme.aut.programsch.repository.OpeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CircleService {

    private CircleRepository circleRepository;

    private OpeningRepository openingRepository;

    @Transactional
    public Page<CircleEntity> findAll(Pageable pageable){
        return circleRepository.findAll(pageable);
    }

    @Transactional
    public List<CircleEntity> findAll(){
        return circleRepository.findAll();
    }

    @Transactional
    public void save(CircleEntity circleEntity){
        circleRepository.save(circleEntity);
    }

    @Transactional
    public CircleEntity findByVirGroupId(Long id){
        return circleRepository.findOneByVirGroupId(id);
    }

}
