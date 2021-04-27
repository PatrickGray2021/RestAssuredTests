package Example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class GET_Examples extends StatusCodeCheck {

    @BeforeTest
    void StatusCode() {
        GetStatusCode("http://dummy.restapiexample.com/api/v1/employees");
    }
    public static Response GetRequest(String endpoint){
        RestAssured.defaultParser = Parser.JSON;

        return
                 given().contentType(ContentType.JSON)
                        .when()
                        .get(endpoint)
                        .then().extract().response();
    }
    @Test
    void test_01() throws InterruptedException {
        System.out.println("TEST 01");
        long startTime = System.currentTimeMillis();

        Response response = GetRequest("http://dummy.restapiexample.com/api/v1/employees");

        List<String> name = response.jsonPath().getList("data.employee_name");

        for (String s : name) {
            System.out.println("Employee name: " + s);

        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        long secs = TimeUnit.MILLISECONDS.toSeconds(executionTime);
        System.out.println("Test run in " + secs + " seconds");
        System.out.println("Sleep for 5 secs");
        Thread.sleep(5000);
    }
    @Test
    void test_02() throws InterruptedException {
        System.out.println("TEST 02");
        long startTime = System.currentTimeMillis();

        Response response = GetRequest("https://jsonplaceholder.typicode.com/users/");

        List<Map<String,String>>idMap = response.jsonPath().getList("address");
        System.out.println(idMap.get(2).get("city"));

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        long secs = TimeUnit.MILLISECONDS.toSeconds(executionTime);
        System.out.println("Test run in " + secs + " seconds");
        System.out.println("Sleep for 5 secs");
        Thread.sleep(5000);


    }
    @Test
    void test_03() throws InterruptedException {
        System.out.println("TEST 03");
        long startTime = System.currentTimeMillis();

        get("https://jsonplaceholder.typicode.com/users/2/").then().assertThat()
                .body(matchesJsonSchemaInClasspath("test.json"));
        Response response = GetRequest("https://jsonplaceholder.typicode.com/users/2/");
        System.out.println(response.prettyPrint());

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        long secs = TimeUnit.MILLISECONDS.toSeconds(executionTime);
        System.out.println("Test run in " + secs + " seconds");
        System.out.println("Sleep for 5 secs");
        Thread.sleep(5000);

    }


    @Test
    void test_04() throws InterruptedException {
        System.out.println("TEST 04");
        String urls = "http://dummy.restapiexample.com/api/v1/employee";
        long startTime = System.currentTimeMillis();


        for(int id =1; id < 24;   id ++ ){

            String sid = String.valueOf(id);
            String newurl = urls + "/" + sid;

            int response = given()
                    .when()
                    .get(newurl).statusCode();

            if (response == 200 || response == 429 ) {
                System.out.println("Status code: " + response);
            }
            else {
                Assert.fail("Status code: " + response);


            }
            if (response == 429){
                System.out.println("429 error message after " + id + " GET requests");
                break;
            }
            if (id > 5){
                Assert.fail("Too many GET requests " + response);
                break;
            }

        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        long secs = TimeUnit.MILLISECONDS.toSeconds(executionTime);
        System.out.println("Test run in " + secs + " seconds");
        System.out.println("Sleep for 5 secs");
        Thread.sleep(5000);

    }
}














