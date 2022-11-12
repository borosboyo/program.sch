
package hu.bme.aut.programsch.controller.membership;

import hu.bme.aut.programsch.domain.Membership;
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
import static org.junit.jupiter.api.Assertions.assertFalse;

//Controller integration test
//With this test type we'll get a fully initialized Spring web application
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MembershipControllerIT {

    private static final String BASE_URL = "/api/membership";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testGettingMemberships() {
        // given, when
        ParameterizedTypeReference<List<Membership>> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<Membership>> response = testRestTemplate.exchange(BASE_URL, HttpMethod.GET, new HttpEntity(null), responseType);
        List<Membership> responseBody = response.getBody();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assert responseBody != null;
        assertFalse(responseBody.isEmpty());
    }
}
