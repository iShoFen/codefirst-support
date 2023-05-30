package fr.iut.uca;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/api/hello")
                .then()
                .statusCode(200);
    }

//    @Test
//    public void testFeedbacksEndpoint() {
//        given()
//                .when().get("/api/feedbacks")
//                .then()
//                .statusCode(200);
//    }

}
