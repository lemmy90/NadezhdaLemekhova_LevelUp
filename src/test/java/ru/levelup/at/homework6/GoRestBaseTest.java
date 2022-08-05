package ru.levelup.at.homework6;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class GoRestBaseTest {

    protected static final String BASE_URL = "https://gorest.co.in/public-api";
    protected static final String SERVICE_PATH_USERS = "/users";
    protected static final String SERVICE_PATH_POSTS = "/posts";
    protected static final String SERVICE_PATH_COMMENTS = "/comments";
    protected static final String TOKEN = "1b98bb85c4ba6dcfe9c0caa0f05d844b354e9e39acbe4f0e3530f235c0c48587";

    protected RequestSpecification requestSpecification;
    protected ResponseSpecification responseSpecification;

    @BeforeSuite
    protected void beforeSuite() {
        RestAssured.baseURI = BASE_URL;
    }

    @BeforeMethod
    protected void setUp() {
        requestSpecification = new RequestSpecBuilder()
            .log(LogDetail.ALL)
            .setContentType(ContentType.JSON)
            .addHeader("Authorization",
                "Bearer " + TOKEN)
            .build();

        responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(200)
            .build();

        //RestAssured.baseURI = BASE_URL; Дима, чекли плиз почему отсюда URI не работает. Мне отдаёт localhost
        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }
}
