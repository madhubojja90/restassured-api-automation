package com.restassured.restfulbooker.tests.PATCH;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class PartialUpdateBookingTest {

    RequestSpecification request= RestAssured.given();
    Response response;
    ValidatableResponse validatableResponse;
    String BASE_URI="https://restful-booker.herokuapp.com";
    String BASE_PATH="/booking" ;
    String bookingId="1655";
    String token="eb90e637dd12a14";
    @Test
    public void partialUpdateBooking_ShouldReturn200_WhenIsValid()
    {

        String BASE_PATH_UPDATED="/booking"+"/"+bookingId;
        String PAYLOAD="{\n" +
                "    \"firstname\" : \"MadhuSudhan\"\n" +
                "}";

        request.baseUri(BASE_URI);
        request.basePath(BASE_PATH_UPDATED);
        request.contentType(ContentType.JSON);
        request.cookie("token",token);
        request.body(PAYLOAD);
       response=request.when().log().all().patch();
        validatableResponse= response.then();
        validatableResponse.log().all().statusCode(200);
        validatableResponse.body("firstname", Matchers.equalTo("MadhuSudhan"));

    }
}
