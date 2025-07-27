package com.restassured.restfulbooker.tests.FULLCURD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FullCrudOnRestfulBookAPI {
    RequestSpecification requestSpecification= RestAssured.given();
    Response response;
    ValidatableResponse validatableResponse;
    String token;
    Integer bookingId;


    String BASE_URI="https://restful-booker.herokuapp.com";

    @BeforeTest
    public void getToken()
    {
        String BASE_PATH="/auth";
        String payload="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        requestSpecification.baseUri(BASE_URI);
        requestSpecification.basePath(BASE_PATH);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload);
        response=requestSpecification.when().log().all().post();
        validatableResponse=response.then().log().all().statusCode(200);
        token=response.then().log().all().extract().path("token");
        System.out.println("Token Generated " +token);
        Assert.assertNotNull(token);
    }

@BeforeTest
public void getBookingId()
{
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
    requestSpecification.baseUri(BASE_URI);
    requestSpecification.basePath(BASE_PATH);
    requestSpecification.contentType(ContentType.JSON);
    requestSpecification.body(PAYLOAD);
    Response response=requestSpecification.when().post();
    ValidatableResponse validatableResponse= response.then();
    validatableResponse.log().all().statusCode(200);
    bookingId=response.then().extract().path("bookingid");
    Assert.assertNotNull(bookingId);
}
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

        requestSpecification.baseUri(BASE_URI);
        requestSpecification.basePath(BASE_PATH_UPDATED);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(PAYLOAD);
        response=requestSpecification.when().log().all().put();
        validatableResponse= response.then();
        validatableResponse.log().all().statusCode(200);
        validatableResponse.body("firstname", Matchers.equalTo("Madhu"));
        validatableResponse.body("lastname", Matchers.equalTo("Bojja"));

        // Using JsonPath for additional manual validation
        String fullJsonResponseString = response.asString();
        JsonPath jsonPath = new JsonPath(fullJsonResponseString);

        Assert.assertEquals(jsonPath.getString("firstname"), "Madhu");
        Assert.assertEquals(jsonPath.getString("lastname"), "Bojja");
        Assert.assertEquals((int) jsonPath.getInt("totalprice"), 111);
        Assert.assertTrue(jsonPath.getBoolean("depositpaid"));
        Assert.assertEquals(jsonPath.getString("bookingdates.checkin"), "2018-01-01");
        Assert.assertEquals(jsonPath.getString("bookingdates.checkout"), "2019-01-01");
        Assert.assertEquals(jsonPath.getString("additionalneeds"), "Breakfast");

        //AsserJ validations

        assertThat(jsonPath.getString("firstname")).isEqualTo("Madhu");
        assertThat(jsonPath.getString("lastname")).isEqualTo("Bojja");
        assertThat(jsonPath.getInt("totalprice")).isEqualTo(111);
        assertThat(jsonPath.getInt("totalprice")).isNotNegative();
        assertThat(jsonPath.getBoolean("depositpaid")).isTrue();
        assertThat(jsonPath.getString("bookingdates.checkin")).isEqualTo("2018-01-01");
        assertThat(jsonPath.getString("bookingdates.checkout")).isEqualTo("2019-01-01");
        assertThat(jsonPath.getString("additionalneeds")).isEqualTo("Breakfast");
    }

}
