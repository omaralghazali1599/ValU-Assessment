package ValU.Assessment;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.isA;

public class ValU {
    @Test
    public void TEST() {
        given().baseUri("https://api.agify.io")
                // Send a GET request with the name parameter
                .when().queryParam("name","meelad").get("/")
                .then().log().all()

                // Assert on the status code
                .assertThat().statusCode(200)

                // Assert that the response won't take more than 4000 milliseconds
                .assertThat().time(lessThan(4000L))

                // Asserting that the body attributes are not null
                .assertThat()
                .body("name",notNullValue())
                .body("age",notNullValue())
                .body("count",notNullValue())

                // Asserting that the body have expected values
                .assertThat()
                .body("name",equalTo("meelad"))
                .body("age", is(greaterThanOrEqualTo(0)))
                .body("count",equalTo(21))

                // Asserting on the type of specific attributes in the response body
                .assertThat()
                .body("name",isA(String.class))
                .body("age",isA(Integer.class))
                .body("count",isA(Integer.class));
    }
}
