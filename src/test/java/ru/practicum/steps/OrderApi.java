package ru.practicum.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.practicum.models.IngredientsResponse;
import ru.practicum.models.OrderRequest;

import static io.restassured.RestAssured.given;
import static ru.practicum.Constants.*;
import static ru.practicum.CustomAllureListener.withCustomTemplates;

public class OrderApi {

    @Step("Отправить запрос на получение ингридиентов")
    public static IngredientsResponse sendGetIngredients() {

        return given()
                .filter(withCustomTemplates())
                .header("Content-type", "application/json")
                .log().all()
                .when()
                .get(GET_INGREDIENTS_URL)
                .body().as(IngredientsResponse.class);
    }


    @Step("Отправить запрос на создание заказа без авторизации")
    public static Response sendPostOrderWithoutAuth(OrderRequest data) {

        return given()
                .filter(withCustomTemplates())
                .header("Content-type", "application/json")
                .body(data)
                .log().all()
                .when()
                .post(POST_ORDERS_URL);
    }

    @Step("Отправить запрос на создание заказа c авторизацией")
    public static Response sendPostOrderWithAuth(OrderRequest data, String token) {

        return given()
                .filter(withCustomTemplates())
                .header("Content-type", "application/json")
                .header("authorization", token)
                .body(data)
                .log().all()
                .when()
                .post(POST_ORDERS_URL);
    }

    @Step("Отправить запрос на создание заказа без ингредиентов")
    public static Response sendPostOrderWithoutIngredients() {

        return given()
                .filter(withCustomTemplates())
                .header("Content-type", "application/json")
                .log().all()
                .when()
                .post(POST_ORDERS_URL);
    }

    @Step("Отправить запрос на получение заказов без авторизации")
    public static Response sendGetOrderWithoutAuth() {

        return given()
                .filter(withCustomTemplates())
                .header("Content-type", "application/json")
                .log().all()
                .when()
                .get(GET_ORDERS_URL);
    }

    @Step("Отправить запрос на получение заказов c авторизацией")
    public static Response sendGetOrderWithAuth(String token) {

        return given()
                .filter(withCustomTemplates())
                .header("Content-type", "application/json")
                .header("authorization", token)
                .log().all()
                .when()
                .get(GET_ORDERS_URL);
    }
}
