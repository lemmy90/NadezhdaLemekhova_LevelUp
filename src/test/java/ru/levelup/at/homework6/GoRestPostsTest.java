package ru.levelup.at.homework6;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import com.github.javafaker.Faker;
import java.util.Random;
import org.testng.annotations.Test;

public class GoRestPostsTest extends GoRestBaseTest {

    @Test
    public void getAllPostsTest() {
        given()
            .when()
            .get(SERVICE_PATH_POSTS)
            .then()
            .body("meta", notNullValue())
            .body("data", notNullValue());
    }

    @Test
    public void addNewPostTest() {
        Faker faker = new Faker();
        int userId = getRandomUserId();
        String title = faker.lorem().words(3).toString();
        String body = faker.lorem().sentences(5).toString();


        var createPostRequest = CreatePostRequestData.builder()
                                                     .title(title)
                                                     .userId(userId)
                                                     .body(body)
                                                     .build();
        given()
            .body(createPostRequest)
            .when()
            .post(SERVICE_PATH_POSTS)
            .then()
            .body("code", equalTo(201))
            .body("data.user_id", equalTo(userId))
            .body("data.title", equalTo(title));
    }

    @Test
    public void getSpecificPostTest() {

        int postId = getRandomPostId();

        given()
            .pathParam("postId", postId)
            .when()
            .get(SERVICE_PATH_POSTS + "/{postId}")
            .then()
            .body("code", equalTo(200))
            .body("data.id", equalTo(postId));
    }

    @Test
    public void updateSpecificPostTest() {

        Faker faker = new Faker();
        int postId = getRandomPostId();
        String userName = faker.name().fullName();
        int userId = getRandomUserId();
        String title = faker.lorem().words(3).toString();
        String body = faker.lorem().sentences(5).toString();



        var postUpdate = CreatePostRequestData.builder()
                                              .user(userName)
                                              .userId(userId)
                                              .title(title)
                                              .body(body)
                                              .build();

        given()
            .pathParam("postId", postId)
            .body(postUpdate)
            .when()
            .put(SERVICE_PATH_POSTS + "/{postId}")
            .then()
            .body("code", equalTo(200))
            .body("data.id", equalTo(postId))
            .body("data.title", equalTo(title));
    }

    @Test
    public void deletePostTest() {

        int postId = getRandomPostId();

        given()
            .pathParam("postId", postId)
            .when()
            .delete(SERVICE_PATH_POSTS + "/{postId}")
            .then()
            .body("code", equalTo(204))
            .body("data", nullValue());
    }

    private int getRandomPostId() {

        Random rand = new Random();
        int randomPostId = rand.nextInt(1500);
        return randomPostId;
    }

    private int getRandomUserId() {

        Random rand = new Random();
        int randomUserId = rand.nextInt(3000);
        return randomUserId;
    }

}
