import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


@RunWith(JUnit4.class)
public class MyApiTest {
    private static final String BASE_PATH = "https://curation-sql.appspot.com";

    @Test
    public void loginTest() throws Exception {
        RestAssured.baseURI = BASE_PATH;
        given()
            .contentType(ContentType.JSON)
            .body("{\"username\": \"jg1\", \"password\": \"jg1pass\"}")
            .post(BASE_PATH + "/authn/login")
            .then()
            .statusCode(200);
    }
}
