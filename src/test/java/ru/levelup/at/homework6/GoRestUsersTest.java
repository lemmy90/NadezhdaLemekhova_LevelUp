package ru.levelup.at.homework6;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import com.github.javafaker.Faker;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.testng.annotations.Test;

public class GoRestUsersTest extends GoRestBaseTest {

    @Test
    public void getAllUsersTest() {
        given()
            .when()
            .get(SERVICE_PATH_USERS)
            .then()
            .body("data.id", notNullValue());
    }

    @Test
    public void addNewUserTest() {

        Faker faker = new Faker();
        String userName = faker.name().fullName();
        String userEmail = faker.internet().emailAddress();
        String userGender = getRandomGender();
        String userState = getRandomState();


        var userRequest = CreateUserRequestData.builder()
                                               .name(userName)
                                               .email(userEmail)
                                               .gender(userGender)
                                               .status(userState)
                                               .build();
        given()
            .body(userRequest)
            .when()
            .post(SERVICE_PATH_USERS)
            .then()
            .body("code", equalTo(201))
            .body("data.name", equalTo(userName))
            .body("data.gender", equalTo(userGender))
            .body("data.email", equalTo(userEmail))
            .body("data.status", equalTo(userState));
    }

    @Test
    public void getSpecificUserTest() {

        int userId = getRandomUserId();

        given()
            .pathParam("userId", userId)
            .when()
            .get(SERVICE_PATH_USERS + "/{userId}")
            .then()
            .body("code", equalTo(200))
            .body("data.id", equalTo(userId))
            .body("meta", equalTo(null))
            .body("data", notNullValue());
    }

    @Test
    public void updateSpecificUserTest() {
        Faker faker = new Faker();

        int userId = getRandomUserId();
        String newUserName = faker.name().fullName();
        String newUserEmail = faker.internet().emailAddress();
        String newUserState = getRandomState();
        String newUserGender = getRandomGender();

        var userUpdate = CreateUserRequestData.builder()
                                              .name(newUserName)
                                              .email(newUserEmail)
                                              .status(newUserState)
                                              .gender(newUserGender)
                                              .build();
        given()
            .pathParam("userId", userId)
            .body(userUpdate)
            .when()
            .put(SERVICE_PATH_USERS + "/{userId}")
            .then()
            .body("code", equalTo(200))
            .body("data.id", equalTo(userId))
            .body("data.name", equalTo(newUserName))
            .body("data.status", equalTo(newUserState))
            .body("data.gender", equalTo(newUserGender))
            .body("data.email", equalTo(newUserEmail));
    }

    @Test
    public void deleteUserTest() {
        int userId = getRandomUserId();

        given()
            .pathParam("userId", userId)
            .when()
            .delete(SERVICE_PATH_USERS + "/{userId}")
            .then()
            .body("code", equalTo(204))
            .body("data", nullValue());
    }

    private String getRandomGender() {

        Random rand = new Random();
        List<String> genderList = Arrays.asList("male", "female");
        String randomGender = genderList.get(rand.nextInt(genderList.size()));
        return randomGender;

    }

    private String getRandomState() {

        Random rand = new Random();
        List<String> stateList = Arrays.asList("active", "inactive");
        String randomState = stateList.get(rand.nextInt(stateList.size()));
        return randomState;

    }

    private int getRandomUserId() {

        int randomUserId = new Random().nextInt(3000);
        return randomUserId;
    }


}
