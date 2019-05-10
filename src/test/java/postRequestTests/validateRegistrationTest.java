package postRequestTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.BaseTest;

public class validateRegistrationTest extends BaseTest {

        RequestSpecification request = RestAssured.given();
        JSONObject requestParams;


        @Test(dataProvider = "getEmailAndPassword", groups="smoke")
        public void validateValidRegistration(String email, String password) {

                request.header("Content-Type","Application/json");
                requestParams = createJSONObject(email, password, request);
                request.body(requestParams.toString());
                Response response = request.post("https://reqres.in/api/register");
                Assert.assertEquals(response.getStatusCode(),201);
        }


        @Test(dataProvider = "getEmailAndPassword", groups="smoke")
        public void validateInvalidRegistration(String email, String password) {

                request.header("Content-Type","Application/json");
                requestParams=createJSONObject(email, null, request);
                request.body(requestParams.toString());
                Response response = request.post("https://reqres.in/api/register");
                Assert.assertEquals(response.getStatusCode(),400);
        }

        private JSONObject createJSONObject(String email, String password, RequestSpecification request) {

                JSONObject requestParams = new JSONObject();

                try {
                        requestParams.put("email", email);
                        requestParams.put("password", password);
                } catch (JSONException e) {
                        e.printStackTrace();
                }
                return requestParams;

        }

        @DataProvider(name ="getEmailAndPassword")
        public Object[][] getEmailAndPassword()
        {
                return new Object[][]
                        {
                                {"sydney@fife", "pascal"}
                        };

        }

}
