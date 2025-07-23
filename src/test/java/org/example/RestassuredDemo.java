package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestassuredDemo {

    public static void main(String[] args) {
        // Set base URL
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Make GET request
        Response response = RestAssured
                .given()
                .get("/posts/1");

        // Print response status code
        System.out.println("Status Code: " + response.getStatusCode());

        // Print response body
        System.out.println("Response Body:");
        System.out.println(response.getBody().asString());
    }
}
