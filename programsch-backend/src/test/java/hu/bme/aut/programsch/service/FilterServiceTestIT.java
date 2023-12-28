package hu.bme.aut.programsch.service;


import java.util.List;

import hu.bme.aut.programsch.domain.Filter;
import hu.bme.aut.programsch.dto.FilterDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class FilterServiceTestIT {

    @Autowired
    private FilterService filterService;

    @Test
    void testGettingFilter() {
        // given, when
        List<Filter> filter = filterService.findAll();

        // then
        assertNotNull(filter);

    }

    @Test
    void testSavingFilter() {
        // given
        Filter filter = new Filter();

        // when
        FilterDto savedFilter = filterService.save(filter);

        // then
        assertNotNull(savedFilter);
    }

    @Test
    void testDeleteFilter() {
        // given
        Filter filter = new Filter();
        filter.setUserId("Test uid");

        // when
        FilterDto savedFilter = filterService.save(filter);
        filterService.delete(savedFilter);

        // then
        assertNull(filterService.findUserFilters(savedFilter.getUserId()));
    }

    @Test
    void testCreateNewFilter() {
        // given, when
        FilterDto savedFilter = filterService.createNewFilter("Test uid");

        // then
        assertNotNull(savedFilter);
    }

}
