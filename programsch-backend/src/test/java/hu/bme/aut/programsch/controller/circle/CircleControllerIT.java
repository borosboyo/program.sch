package hu.bme.aut.programsch.controller.circle;

import hu.bme.aut.programsch.domain.Circle;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CircleControllerIT {

    private static final String BASE_URL = "/api/circle";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testGettingCircles() {
        // given, when
        ParameterizedTypeReference<List<Circle>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<Circle>> response = testRestTemplate.exchange(BASE_URL, HttpMethod.GET, new HttpEntity(null), responseType);
        List<Circle> responseBody = response.getBody();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(responseBody);
    }

}
