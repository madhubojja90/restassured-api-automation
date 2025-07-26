package com.restassured.restfulbooker.tests.DELETE;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class DeleteBookingTest {

    RequestSpecification request= RestAssured.given();
    Response response;
    ValidatableResponse validatableResponse;
    String BASE_URI="https://restful-booker.herokuapp.com";
    String bookingId="1655";
    String token="ab65fcd287c09f6";
    @Test
    public void deleteBooking_ShouldReturn201_WhenIsValid()
    {

        String BASE_PATH_UPDATED="/booking"+"/"+bookingId;
        request.baseUri(BASE_URI);
        request.basePath(BASE_PATH_UPDATED);
        request.cookie("token",token);
        response=request.when().log().all().delete();
        validatableResponse= response.then();
        validatableResponse.log().all().statusCode(201);

    }
}
