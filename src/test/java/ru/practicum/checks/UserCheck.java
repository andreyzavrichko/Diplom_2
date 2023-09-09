package ru.practicum.checks;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class UserCheck {
    @Step("Проверить создание пользователя")
    public static void checkCreateUser(Response response) {
        response.then().assertThat().body("success", equalTo(true)).statusCode(SC_OK);
    }

    @Step("Проверить создание пользователя существующего в системе")
    public static void checkCreateDuplicateUser(Response response) {
        response.then().assertThat().body("success", equalTo(false)).statusCode(SC_FORBIDDEN);
        response.then().assertThat().body("message", equalTo("User already exists"));
    }

    @Step("Проверить создание пользователя без заполнения полей")
    public static void checkCreateRequiredFieldsUser(Response response) {
        response.then().assertThat().body("success", equalTo(false)).statusCode(SC_FORBIDDEN);
        response.then().assertThat().body("message", equalTo("Email, password and name are required fields"));
    }

    @Step("Проверить получение данных пользователя")
    public static void checkDataUser(Response response, String email, String name) {
        response.then().assertThat().body("success", equalTo(true)).statusCode(SC_OK);
        response.then().assertThat().body("user.email", equalTo(email));
        response.then().assertThat().body("user.name", equalTo(name));
    }


    @Step("Проверить изменение пользователя без авторизации")
    public static void checkChangeUserWithoutAuthUser(Response response) {
        response.then().assertThat().body("success", equalTo(false)).statusCode(SC_UNAUTHORIZED);
        response.then().assertThat().body("message", equalTo("You should be authorised"));
    }

    @Step("Проверить удаление пользователя")
    public static void checkDeleteUser(Response response) {
        response.then().assertThat().body("success", equalTo(true)).statusCode(SC_ACCEPTED);
    }
}
