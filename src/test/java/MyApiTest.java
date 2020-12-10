import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class MyApiTest {
    private static final String BASE_PATH = "https://curation-sql.appspot.com";
    private static final String USER_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImpnMSJ9.KAYQ5Fa0GJHm63NH0DiB758xNIv1MAt9Kcdic_jBxvw";

    MyApiTest() {
        RestAssured.baseURI =BASE_PATH;
    }

    @DataProvider(name="credentialProvider")
    Object[][] provideCredentials() {
        return new Object[][] {
                {"jg1", "jg1pass", 200,"{\"token\": \"" + USER_TOKEN + "\", \"username\": \"jg1\"}"},
                {"jg1", "jg2pass", 400, "{\"message\": \"Provided credentials are invalid.\"}"},
                {"", "jg2pass", 500, "{\"message\": \"Internal Server Error\"}"},
        };
    }

    @Test(dataProvider = "credentialProvider")
    public void loginTest(String username, String password, int statusCode, String responseBody) throws Exception {
        given()
                .contentType(ContentType.JSON)
                .body("{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}")
                .post(BASE_PATH + "/authn/login")
                .then()
                .statusCode(statusCode)
                .assertThat()
                .body(Matchers.equalTo( responseBody));
    }

    @Test
    public void loginTestSuccess() throws Exception {

        given()
            .contentType(ContentType.JSON)
            .body("{\"username\": \"jg1\", \"password\": \"jg1pass\"}")
            .post(BASE_PATH + "/authn/login")
            .then()
            .statusCode(200)
            .assertThat()
            .body(Matchers.equalTo("{\"token\": \"" + USER_TOKEN + "\", \"username\": \"jg1\"}"));
    }

    @Test
    public void loginTestFailure() throws Exception {

        given()
                .contentType(ContentType.JSON)
                .body("{\"username\": \"jg1\", \"password\": \"jg1pass1\"}")
                .post(BASE_PATH + "/authn/login")
                .then()
                .statusCode(400)
                .assertThat()
                .body(Matchers.equalTo("{\"message\": \"Provided credentials are invalid.\"}"));

    }

    @Test
    public void verifyProfile() throws Exception {
        RestAssured.baseURI = BASE_PATH;
        given()
                .header(new Header("Authorization", "Token jg1/" + USER_TOKEN))
                .get(BASE_PATH + "/authn/profile")
                .then()
                .statusCode(200);
    }

    @Test
    public void verifyProfileFailure() throws Exception {
        given()
                .header(new Header("Authorization", "Token jg1/1" + USER_TOKEN))
                .get(BASE_PATH + "/authn/profile")
                .then()
                .statusCode(403);
    }
}
