package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.config.db.DbConfigService;
import hu.bme.aut.programsch.model.Circle;
import hu.bme.aut.programsch.repository.CircleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CircleService {

    private final CircleRepository circleRepository;

    private final DbConfigService dbConfigService;

    @Transactional
    public Page<Circle> findAll(Pageable pageable) {
        return circleRepository.findAll(pageable);
    }

    @Transactional
    public List<Circle> findAll() {
        dbConfigService.injectCirclesIntoDb();
        return circleRepository.findAll();
    }

    @Transactional
    public void save(Circle circleEntity) {
        circleRepository.save(circleEntity);
    }

    @Transactional
    public Circle findByVirGroupId(Long id) {
        return circleRepository.findOneByVirGroupId(id);
    }



}
