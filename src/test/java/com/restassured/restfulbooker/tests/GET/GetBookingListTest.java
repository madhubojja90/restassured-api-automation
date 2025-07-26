package com.restassured.restfulbooker.tests.GET;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class GetBookingListTest {
    @Test
    public void getBookingList_ReturnsBookingIds_WhenEndpointIsCorrect() {
        RequestSpecification r = RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com/booking");
        Response response = r.when().log().all().get();
        ValidatableResponse validatableResponse = response
                .then()
                .log().all()
                .statusCode(200)
                .time(lessThan(3000L))
                .contentType("application/json")
                .body("size()",greaterThan(0));

    }

    @Test
    public void getBookingList_ReturnsNotFound_WhenEndpointIsIncorrect() {
        RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com/bookings") // wrong endpoint
                .when()
                .log().all()
                .get()
                .then()
                .log().all()
                .statusCode(404); // Not Found
    }

}
