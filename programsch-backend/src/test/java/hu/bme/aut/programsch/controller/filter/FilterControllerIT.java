
package hu.bme.aut.programsch.controller.filter;

import hu.bme.aut.programsch.domain.Filter;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

//Controller integration test
//With this test type we'll get a fully initialized Spring web application
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FilterControllerIT {

    private static final String BASE_URL = "/api/filter";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testGettingFilters() {
        // given, when
        ParameterizedTypeReference<Filter> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<Filter> response = testRestTemplate.exchange(BASE_URL, HttpMethod.GET, new HttpEntity(null), responseType);
        Filter responseBody = response.getBody();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(responseBody);
    }
}
