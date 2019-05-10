package utilities;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp()

    {


        //RestAssured.baseURI="https://reqres.in/api";


    }
}
