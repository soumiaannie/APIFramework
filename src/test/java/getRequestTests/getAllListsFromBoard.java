package getRequestTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;


public class getAllListsFromBoard {

    @Test
    public void getAllListsFromBoard() {
        RequestSpecification request = RestAssured.given().queryParam("key", "0697ace29da135af1009cc535346c753").queryParam("token", "9d0b78f9ee4b490012fe0515e27cdf434fb5853da2bc2023ad91829004c333cb");
        request.header("Content-Type", "Application/json");
        request.pathParam("id", "5ccc13fd042ee72f21beda99");
        Response response = request.get("https://api.trello.com/1/boards/{id}/lists");
        response.getBody().print();
        ArrayList<HashMap<String, String>> responseBody = new ArrayList<HashMap<String, String>>();
        responseBody = response.jsonPath().get();

        ArrayList<String> actualListNames = new ArrayList<String>();
        for (int i = 0; i < responseBody.size(); i++) {
            actualListNames.add(responseBody.get(i).get("name"));
        }


        ArrayList<String> expectedListNames = new ArrayList<String>();
        expectedListNames.add("Doing");
        expectedListNames.add("Done");
        expectedListNames.add("MyNewList");
        expectedListNames.add("To Do");


        int size = responseBody.size();
        System.out.println(size);
        System.out.println(responseBody);

        /*for(int i =0; i<size;i++)
        {
            String listid=responseBody.get(i).get("id");
            System.out.println(listid);
            String listName=responseBody.get(i).get("name");
            System.out.println(listName);


        }*/

        response.getBody().jsonPath().get("id");
        System.out.println(expectedListNames.containsAll(actualListNames));
        System.out.println(expectedListNames);
        System.out.println(actualListNames);


    }
}
