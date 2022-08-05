package ru.levelup.at.homework6;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import com.github.javafaker.Faker;
import java.util.Random;
import org.testng.annotations.Test;

public class GoRestCommentsTest extends GoRestBaseTest {

    @Test
    public void getAllCommentsTest() {
        given()
            .when()
            .get(SERVICE_PATH_COMMENTS)
            .then()
            .body("meta", notNullValue())
            .body("data", notNullValue());
    }

    @Test
    public void addNewCommentTest() {
        Faker faker = new Faker();
        int postId = getRandomPostId();
        String userName = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String body = faker.lorem().sentences(5).toString();



        var newCommentRequest = CreateCommentRequestData.builder()
                                                        .postId(postId)
                                                        .name(userName)
                                                        .email(email)
                                                        .body(body)
                                                        .build();

        given()
            .body(newCommentRequest)
            .when()
            .post(SERVICE_PATH_COMMENTS)
            .then()
            .body("code", equalTo(201))
            .body("data.name", equalTo(userName))
            .body("data.post_id", equalTo(postId));
    }

    @Test
    public void getSpecificCommentTest() {

        int commentId = getRandomCommentId();

        given()
            .pathParam("commentId", commentId)
            .when()
            .get(SERVICE_PATH_COMMENTS + "/{commentId}")
            .then()
            .body("code", equalTo(200))
            .body("data.id", equalTo(commentId));
    }

    @Test
    public void updateSpecificCommentTest() {
        Faker faker = new Faker();
        int commentId = getRandomCommentId();
        int postId = getRandomPostId();
        String userName = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String body = faker.lorem().sentences(5).toString();


        var updateCommentRequest = CreateCommentRequestData.builder()
                                                           .email(email)
                                                           .name(userName)
                                                           .postId(postId)
                                                           .body(body)
                                                           .build();
        given()
            .pathParam("commentId", commentId)
            .body(updateCommentRequest)
            .when()
            .put(SERVICE_PATH_COMMENTS + "/{commentId}")
            .then()
            .body("code", equalTo(200))
            .body("data.id", equalTo(commentId))
            .body("data.name", equalTo(userName))
            .body("data.body", equalTo(body));
    }

    @Test
    public void deleteCommentTest() {

        long commentId = getRandomCommentId();

        given()
            .pathParam("commentId", commentId)
            .when()
            .delete(SERVICE_PATH_COMMENTS + "/{commentId}")
            .then()
            .body("code", equalTo(204))
            .body("data", nullValue());
    }

    public int getRandomCommentId() {

        Random rand = new Random();
        int randomCommentId = rand.nextInt(1700);
        return  randomCommentId;

    }

    public int getRandomPostId() {

        Random rand = new Random();
        int randomPostId = rand.nextInt(1500);
        return  randomPostId;

    }

}
