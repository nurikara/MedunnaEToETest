package stepdefinitions.api_test;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.RoomPojo;

import java.util.HashMap;
import java.util.Map;

import static baseUrls.MeddunnaBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class RoomCreationsStepDefs {
    RoomPojo expectedData;
    Response response;
    int roomNumberFaker2;
    Map<String, Object> expectedDataMap;
    @Given("send post request for creating room")
    public void send_post_request_for_creating_room() {

        // Set the Url

        spec.pathParams("pp1", "api", "pp2", "rooms");

        //set the expectedData

        //1. Yol: String --> Tavsiye edilmez
        String expectedDataString = "{ \"description\": \"Created By API\",  \"price\": 200, \"roomNumber\": " + roomNumberFaker2 + ", \"roomType\": \"TWIN\", \"status\": true}";

        //2. Yol: Map
        expectedDataMap = new HashMap<>();
        expectedDataMap.put("description", "Created By API");
        expectedDataMap.put("price", 200);
        expectedDataMap.put("roomNumber", roomNumberFaker2);
        expectedDataMap.put("roomType", "TWIN");
        expectedDataMap.put("status", true);

//3. yol pojo ile

        roomNumberFaker2 = Faker.instance().number().numberBetween(10000, 1000000);
        expectedData = new RoomPojo(roomNumberFaker2, "TWIN", true, 200.0, "Cinli Perili Oda, ");

        // Send the request get the response

        response = given(spec).body(expectedData).post("{pp1}/{pp2}");

        response.prettyPrint();

    }

    @Then("get the response and validate")
    public void get_the_response_and_validate() throws JsonProcessingException {

        //Do assertions
        //1. 1.Yol then() methodu ile alinan datayi hamcrestmatcher ile dogrulama

        response.
                then().
                statusCode(201).
                body("roomNumber", equalTo(roomNumberFaker2),
                        "roomType", equalTo("TWIN"),
                        "status", equalTo(true), "description", equalTo("Cinli Perili Oda,"));

        //2.Yol JsonPath ile dogrulama
        JsonPath jsonPath = response.jsonPath();

        assertEquals(roomNumberFaker2, jsonPath.getInt("roomNumber"));
        assertEquals("TWIN", jsonPath.getString("roomType"));
        assertEquals(200.0, jsonPath.getDouble("Price"));
        assertEquals("True", jsonPath.getBoolean("status"));


        //3. Yol: Map ile
        Map<String, Object> actualDataMap = response.as(HashMap.class);

        assertEquals(201, response.statusCode());
        assertEquals(expectedDataMap.get("roomNumber"), actualDataMap.get("roomNumber"));
        assertEquals(expectedDataMap.get("roomType"), actualDataMap.get("roomType"));
        assertEquals(expectedDataMap.get("status"), actualDataMap.get("status"));
        assertEquals(expectedDataMap.get("price"), actualDataMap.get("price"));
        assertEquals(expectedDataMap.get("description"), actualDataMap.get("description"));

        //4. yol

       RoomPojo actualDataPojo= response.as(RoomPojo.class);

       assertEquals(expectedData.getDescription(),actualDataPojo.getDescription());
       assertEquals(expectedData.getRoomNumber(),actualDataPojo.getRoomNumber());
       assertEquals(expectedData.getRoomType(),actualDataPojo.getRoomType());
       assertEquals(expectedData.getStatus(),actualDataPojo.getStatus());
       assertEquals(expectedData.getPrice(),actualDataPojo.getPrice());

       //5. yol Object Mapper + Pojo

        RoomPojo actualDataPojoMapper=new ObjectMapper().readValue(response.asString(), RoomPojo.class);

        assertEquals(expectedData.getRoomNumber(), actualDataPojoMapper.getRoomNumber());
        assertEquals(expectedData.getRoomType(), actualDataPojoMapper.getRoomType());
        assertEquals(expectedData.getStatus(), actualDataPojoMapper.getStatus());
        assertEquals(expectedData.getPrice(), actualDataPojoMapper.getPrice());
        assertEquals(expectedData.getDescription(), actualDataPojoMapper.getDescription());

        //6.yol Gson + Pojo


        //6. Yol: Gson + Pojo
        RoomPojo actualDataPojoGson = new Gson().fromJson(response.asString(), RoomPojo.class);
        System.out.println("actualDataPojoGson = " + actualDataPojoGson);

        assertEquals(expectedData.getRoomNumber(), actualDataPojoGson.getRoomNumber());
        assertEquals(expectedData.getRoomType(), actualDataPojoGson.getRoomType());
        assertEquals(expectedData.getStatus(), actualDataPojoGson.getStatus());
        assertEquals(expectedData.getPrice(), actualDataPojoGson.getPrice());
        assertEquals(expectedData.getDescription(), actualDataPojoGson.getDescription());


    }
}
