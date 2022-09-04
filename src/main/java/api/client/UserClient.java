package api.client;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.CreateUserRequest;
import models.UserLoginRequest;

import static io.restassured.RestAssured.given;

public class UserClient{

    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";

    private final String REGISTER_URI = "/api/auth/register";
    private final String LOGIN_URI = "/api/auth/login";
    private final String USER_URI = "/api/auth/user";

    public UserClient() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Step("Create user")
    public Response createUser(CreateUserRequest createUserRequest) {
        return given()
                .body(createUserRequest)
                .when()
                .post(REGISTER_URI);
    }

    @Step("User login")
    public String userGetToken(UserLoginRequest userLoginRequest) {
        return given()
                .body(userLoginRequest)
                .post(LOGIN_URI)
                .then().extract().body().path("accessToken");
    }

    @Step("Delete user")
    public void deleteUser(String token) {
        given()
                .header("Authorization", token)
                .when()
                .delete(USER_URI)
                .then().statusCode(202);
    }
}