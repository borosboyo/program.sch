package hu.bme.aut.programsch.service;


import java.util.List;

import hu.bme.aut.programsch.domain.Circle;
import hu.bme.aut.programsch.dto.CircleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CircleServiceTestIT {

    @Autowired
    private CircleService circleService;

    @Test
    void testGettingCircle() {
        // given, when
        List<CircleDto> circleDtos = circleService.findAll();

        // then
        assertEquals(61, circleDtos.size());
        assertNotNull(circleService.findAll());

    }

    @Test
    void testSavingCircle() {
        // given
        Circle circle = new Circle();

        // when
        CircleDto savedCircle = circleService.save(circle);

        // then
        assertNotNull(savedCircle);
    }

}
