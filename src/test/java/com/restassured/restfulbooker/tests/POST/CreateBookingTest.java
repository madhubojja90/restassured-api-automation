package com.restassured.restfulbooker.tests.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class CreateBookingTest {

    @Test
    public void createBooking_ShouldReturn200_WhenIsValid()
    {
        String BASE_URI="https://restful-booker.herokuapp.com";
        String BASE_PATH="/booking" ;
        String PAYLOAD="{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        RequestSpecification request= RestAssured.given();
        request.baseUri(BASE_URI);
        request.basePath(BASE_PATH);
        request.contentType(ContentType.JSON);
        request.body(PAYLOAD);
        Response response=request.when().post();
        ValidatableResponse validatableResponse= response.then();
        validatableResponse.log().all().statusCode(200);
    }
}
