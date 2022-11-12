package hu.bme.aut.programsch.service;


import java.util.List;

import hu.bme.aut.programsch.dto.ResortDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ResortServiceTestIT {

    @Autowired
    private ResortService resortService;

    @Test
    void testGettingResort() {
        // given, when
        List<ResortDto> resortDtos = resortService.findAll();

        // then
        assertEquals(11, resortDtos.size());
        assertNotNull(resortService.findAll());

    }

    @Test
    void testGettingResortByName() {
        // given, when
        ResortDto resortDto = resortService.findByName("Egyéb");

        // then
        assertNotNull(resortDto);
    }

    @Test
    void testGettingResortByCircle() {
        // given, when
        ResortDto resortDto = resortService.findResortByCircle("Schörpong");

        // then
        assertNotNull(resortDto);
    }

    @Test
    void testGettingResortByMemberships() {
        // given, when
        List<ResortDto> resortDtos = resortService.findByMemberships();

        // then
        //1 user van a db-ben, aki 5 körben van benne, így 4 resortban
        assertNotNull(resortDtos);
        assertEquals(4, resortDtos.size());
    }
}
