package getRequestTests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidateResponseCodeTest extends BaseTest {

    @Test(groups="smoke")
    public void validateResponeCode ()
    {
        //Creating the expected data List for the users in page2
        ArrayList<Map<String, String>> expectedListOfUsers = new ArrayList<Map<String, String>>();
        HashMap<String, String> expectedUser1 = new HashMap<String, String>();
        expectedUser1.put("id", "4");
        expectedUser1.put("firstName", "Eve");
        expectedUser1.put("lastName", "Holt");
        expectedListOfUsers.add(expectedUser1);
        HashMap<String, String> expectedUser2 = new HashMap<String, String>();
        expectedUser2.put("id", "5");
        expectedUser2.put("firstName", "Charles");
        expectedUser2.put("lastName", "Morris");
        expectedListOfUsers.add(expectedUser2);
        HashMap<String, String> expectedUser3 = new HashMap<String, String>();
        expectedUser3.put("id", "6");
        expectedUser3.put("firstName", "Tracey");
        expectedUser3.put("lastName", "Ramos");
        expectedListOfUsers.add(expectedUser3);


        //Getting the response and asserting on response code
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        Assert.assertTrue(response.getStatusCode()==200);


        //Getting the list of actual users from response and comparing firstname and lastnme and asserting
        List<Map<String, String>> actualListOfUsers =response.jsonPath().getList("data");
        for (int i=0; i< actualListOfUsers.size(); i++) {

          String fname = actualListOfUsers.get(i).get("first_name");
          String fnameExpected=expectedListOfUsers.get(i).get("firstName");
          Assert.assertEquals(fname, fnameExpected);

          String lname = actualListOfUsers.get(i).get("last_name");
          String lnameExpected=expectedListOfUsers.get(i).get("lastName");
          Assert.assertEquals(lname, lnameExpected);

        }

    }

}
