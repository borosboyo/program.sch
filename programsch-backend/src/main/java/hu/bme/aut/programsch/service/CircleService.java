package hu.bme.aut.programsch.service;

import hu.bme.aut.programsch.config.db.DbConfigService;
import hu.bme.aut.programsch.dto.CircleDto;
import hu.bme.aut.programsch.mapper.CircleMapper;
import hu.bme.aut.programsch.domain.Circle;
import hu.bme.aut.programsch.repository.CircleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CircleService {

    private final CircleRepository circleRepository;

    private final DbConfigService dbConfigService;

    private final CircleMapper circleMapper;

    @Transactional
    public List<CircleDto> findAll() {
        dbConfigService.injectCirclesIntoDb();
        return circleMapper.circlesDtos(circleRepository.findAll());
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
