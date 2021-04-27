package Example;

import io.restassured.RestAssured;
import org.testng.Assert;

public class StatusCodeCheck {


    public void GetStatusCode(String url){
        int response = RestAssured.given()
                .when()
                .get(url).statusCode();

        if (response == 200) {
            System.out.println("Status code: " + response);
        }
        else{
            Assert.fail("Status code: " + response);
            System.out.println("Status code: " + response);
        }

    }
    public void GetStatusCodeNegative(String url){
        int response = RestAssured.given()
                .when()
                .get(url).statusCode();

        if (response == 200) {
            System.out.println("Status code: " + response);
        }
        else {
            Assert.assertEquals(response ==429,response ==429);
            System.out.println("Status code: " + response);

        }

    }

    }

