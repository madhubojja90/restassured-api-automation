package com.restassured.restfulbooker.tests.PUT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class FullUpdateBookingTest {

    RequestSpecification request= RestAssured.given();
    Response response;
    ValidatableResponse validatableResponse;
    String BASE_URI="https://restful-booker.herokuapp.com";
    String BASE_PATH="/booking" ;
    String bookingId="1655";
    String token="ae9a57c8b7ceab8";
    @Test
    public void fullUpdateBooking_ShouldReturn200_WhenIsValid()
    {

        String BASE_PATH_UPDATED="/booking"+"/"+bookingId;
        String PAYLOAD="{\n" +
                "    \"firstname\" : \"Madhu\",\n" +
                "    \"lastname\" : \"Bojja\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        request.baseUri(BASE_URI);
        request.basePath(BASE_PATH_UPDATED);
        request.contentType(ContentType.JSON);
        request.cookie("token",token);
        request.body(PAYLOAD);
       response=request.when().log().all().put();
        validatableResponse= response.then();
        validatableResponse.log().all().statusCode(200);
        validatableResponse.body("firstname", Matchers.equalTo("Madhu"));
        validatableResponse.body("lastname", Matchers.equalTo("Bojja"));
    }
}
