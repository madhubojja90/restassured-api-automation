package com.testing.restassured.handson;

import org.testng.annotations.*;

public class TestNGAnnotationsExample {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite: Project setup cheyyadam...");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite: Project report close cheyyadam...");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test: Test block start...");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After Test: Test block end...");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class: Class setup...");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class: Cleanup test data...");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method: Login cheyyadam...");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method: Logout cheyyadam...");
    }

    @Test
    public void test1() {
        System.out.println("Running Test 1: Homepage title test...");
    }

    @Test
    public void test2() {
        System.out.println("Running Test 2: Login button test...");
    }
}

