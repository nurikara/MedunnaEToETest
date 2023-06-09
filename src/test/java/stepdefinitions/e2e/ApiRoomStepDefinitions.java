

package stepdefinitions.e2e;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import pojos.RoomPojo;

import static baseUrls.MeddunnaBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static stepdefinitions.e2e.MedunnaRoomCreationsStepDefinitions.roomNumberFaker;
import static stepdefinitions.e2e.MedunnaRoomCreationsStepDefinitions.roomId;


public class ApiRoomStepDefinitions {
    Response response;
    RoomPojo expectedData;

    @Given("send get request to url")
    public void send_get_request_to_url() {
        //Set the url -->https://medunna.com/api/rooms?sort=createdDate,desc
        spec.pathParams("first", "api", "second", "rooms")
                .queryParams("sort", "createdDate,desc");

        //Set the expected data

        //Send the request and get the response
        response = given(spec).get("{first}/{second}");
        response.prettyPrint();


    }

    @When("validate body")
    public void validate_body() {

        Object roomType = response.jsonPath().getList("findAll{it.roomNumber=="+ roomNumberFaker +"}.roomType").get(0);
        Object status = response.jsonPath().getList("findAll{it.roomNumber=="+ roomNumberFaker +"}.status").get(0);
        Object price = response.jsonPath().getList("findAll{it.roomNumber=="+ roomNumberFaker +"}.price").get(0);
        Object description = response.jsonPath().getList("findAll{it.roomNumber=="+ roomNumberFaker +"}.description").get(0);

        assertEquals(200, response.getStatusCode());
        assertEquals("PREMIUM_DELUXE", roomType);
        assertEquals(true, status);
        assertEquals("123.0", price + "");
        assertEquals("Deniz manzarali havali oda", description);


    }

    @Given("send get request by Id")
    public void sendGetRequestById() {
        //Set the url -->https://medunna.com/api/rooms?sort=createdDate,desc
        spec.pathParams("first", "api", "second", "rooms","third",roomId );

        //Set the expected data
        expectedData = new RoomPojo(roomNumberFaker,"PREMIUM_DELUXE",true,123.00,"Deniz manzarali havali oda");

        //Send the request and get the response
        response = given(spec).get("{first}/{second}/{third}");
        response.prettyPrint();



    }

    @When("validate response body")
    public void validateResponseBody() throws JsonProcessingException {
        //Do assertion
        RoomPojo actualData = new ObjectMapper().readValue(response.asString(), RoomPojo.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getRoomNumber(), actualData.getRoomNumber());
        assertEquals(expectedData.getPrice(), actualData.getPrice());
        assertEquals(expectedData.getStatus(), actualData.getStatus());
        assertEquals(expectedData.getDescription(), actualData.getDescription());
        assertEquals(expectedData.getRoomType(), actualData.getRoomType());

    }
}