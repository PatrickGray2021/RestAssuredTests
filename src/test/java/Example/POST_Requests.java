package Example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class POST_Requests {

    private static String requestBody = "{\n" +
            "  \"title\": \"Hello World\",\n" +
            "  \"body\": \"Patrick\",\n" +
            "  \"userId\": \"1\" ,\n" +
            "  \"LongName\": \"Patrick Joseph Geroge Gray\" \n}";
    File json = new File("C:\\Users\\patrick.gray\\Desktop\\RestAssuredTests\\src\\test\\resources\\requestBody.json");

    @BeforeTest
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

    }

    @Test
    public void postRequestUsingFile() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/posts")
                .then()
                .extract().response();

        Assert.assertEquals(201, response.statusCode());
        Assert.assertEquals("Hello World",response.jsonPath().getString("tile"));
        Assert.assertEquals("Patrick",response.jsonPath().getString("body"));
        Assert.assertEquals(1,response.jsonPath().getInt("UserId"));

    }

    @Test
    public void postRequestUsingString() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .extract().response();

        Assert.assertEquals(201, response.statusCode());
        Assert.assertEquals("Hello World",response.jsonPath().getString("title"));
        Assert.assertEquals("Patrick",response.jsonPath().getString("body"));
        Assert.assertEquals("1",response.jsonPath().getString("userId"));
        Assert.assertEquals("Patrick Joseph Geroge Gray",response.jsonPath().getString("LongName"));





    }
}


