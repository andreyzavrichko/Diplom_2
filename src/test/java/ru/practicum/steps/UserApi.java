package ru.practicum.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.practicum.models.CreateUserRequest;

import static io.restassured.RestAssured.given;
import static ru.practicum.Constants.*;
import static ru.practicum.CustomAllureListener.withCustomTemplates;

public class UserApi {
    @Step("Отправить запрос на создание пользователя")
    public static Response sendPostRequestCreateUser(CreateUserRequest data) {

        return given()
                .filter(withCustomTemplates())
                .header("Content-type", "application/json")
                .body(data)
                .log().all()
                .when()
                .post(CREATE_USER_URL);
    }

    @Step("Отправить запрос на получение данных пользователя")
    public static Response sendGetRequestUser(String token) {

        return given()
                .filter(withCustomTemplates())
                .header("Content-type", "application/json")
                .header("authorization", token)
                .log().all()
                .when()
                .get(GET_USER_URL);
    }

    @Step("Отправить запрос на обновление данных пользователя с токеном")
    public static Response sendPatchRequestUser(CreateUserRequest data, String token) {

        return given()
                .filter(withCustomTemplates())
                .header("Content-type", "application/json")
                .header("authorization", token)
                .body(data)
                .log().all()
                .when()
                .patch(PATCH_USER_URL);
    }

    @Step("Отправить запрос на обновление данных пользователя без токена")
    public static Response sendPatchRequestWithoutAuthUser(CreateUserRequest data) {

        return given()
                .filter(withCustomTemplates())
                .header("Content-type", "application/json")
                .body(data)
                .log().all()
                .when()
                .patch(PATCH_USER_URL);
    }

    @Step("Удалить пользователя")
    public static Response deleteUser(String token) {
        return given()
                .header("Authorization", token)
                .log().all()
                .when()
                .delete(DELETE_USER_URL);
    }


}
