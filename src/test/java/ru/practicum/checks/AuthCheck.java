package ru.practicum.checks;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.equalTo;

public class AuthCheck {
    @Step("Проверить авторизацию пользователя")
    public static void checkAuthUser(Response response) {
        response.then().assertThat().body("success", equalTo(true)).statusCode(SC_OK);
    }

    @Step("Проверить авторизацию пользователя с неверным логином и паролем")
    public static void checkAuthInvalidLoginPass(Response response) {
        response.then().assertThat().body("success", equalTo(false)).statusCode(SC_UNAUTHORIZED);
        response.then().assertThat().body("message", equalTo("email or password are incorrect"));
    }


}
