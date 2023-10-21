package ru.practicum.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.practicum.models.AuthRequest;

import static io.restassured.RestAssured.given;
import static ru.practicum.Constants.AUTH_URL;
import static ru.practicum.CustomAllureListener.withCustomTemplates;

public class AuthApi {
    @Step("Отправить запрос на авторизацию")
    public static Response sendPostRequestAuth(AuthRequest data) {

        return given()
                .filter(withCustomTemplates())
                .header("Content-type", "application/json")
                .body(data)
                .log().all()
                .when()
                .post(AUTH_URL);
    }


    @Step("Получить токен")
    public static String getAuthToken(Response response) {
        return response.body().jsonPath().get("accessToken");
    }
}
