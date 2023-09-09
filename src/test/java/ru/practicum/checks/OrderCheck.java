package ru.practicum.checks;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Assert;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class OrderCheck {
    @Step("Проверить создание заказа без авторизации")
    public static void checkCreateOrderWithoutAuth(Response response, String name) {
        response.then().assertThat().body("success", equalTo(true)).statusCode(SC_OK);
        response.then().assertThat().body("name", equalTo(name));
    }

    @Step("Проверить создание заказа без ингредиентов")
    public static void checkCreateOrderWithoutIngredients(Response response) {
        response.then().assertThat().body("success", equalTo(false)).statusCode(SC_BAD_REQUEST);
        response.then().assertThat().body("message", equalTo("Ingredient ids must be provided"));
    }

    @Step("Проверить создание заказа с неверными хэшами ингредиентов")
    public static void checkCreateOrderIncorrectIngredients(Response response) {
        response.then().assertThat().body("success", equalTo(false)).statusCode(SC_BAD_REQUEST);
        response.then().assertThat().body("message", equalTo("One or more ids provided are incorrect"));
    }

    @Step("Проверить получение заказа без авторизации")
    public static void checkGetOrderWithoutAuth(Response response) {
        response.then().assertThat().body("success", equalTo(false)).statusCode(SC_UNAUTHORIZED);
        response.then().assertThat().body("message", equalTo("You should be authorised"));
    }

    @Step("Проверить получение заказа c авторизацией")
    public static void checkGetOrderWithAuth(Response response) {
        response.then().assertThat().body("success", equalTo(true)).statusCode(SC_OK);
        Assert.assertNotNull(response);
    }
}
