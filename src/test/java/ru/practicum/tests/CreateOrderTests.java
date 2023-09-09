package ru.practicum.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.models.CreateUserRequest;
import ru.practicum.models.OrderRequest;

import static ru.practicum.Constants.BASE_URL;
import static ru.practicum.checks.OrderCheck.*;
import static ru.practicum.checks.UserCheck.checkCreateUser;
import static ru.practicum.checks.UserCheck.checkDeleteUser;
import static ru.practicum.data.DataGenerator.*;
import static ru.practicum.steps.AuthApi.getAuthToken;
import static ru.practicum.steps.OrderApi.*;
import static ru.practicum.steps.UserApi.deleteUser;
import static ru.practicum.steps.UserApi.sendPostRequestCreateUser;
@DisplayName("Заказы")
public class CreateOrderTests {

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @Feature("Order")
    @Story("Order")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание заказа без авторизации")
    public void createOrderWithoutAuthTest() {
        OrderRequest data = getIngredients();
        Response response = sendPostOrderWithoutAuth(data);
        checkCreateOrderWithoutAuth(response, "Экзо-плантаго бургер");
    }

    @Test
    @Feature("Order")
    @Story("Order")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание заказа c авторизацией")
    public void createOrderWithAuthTest() {
        CreateUserRequest data = getUser();
        // Создать пользователя
        Response response = sendPostRequestCreateUser(data);
        checkCreateUser(response);

        // Получаем токен
        String token = getAuthToken(response);
        OrderRequest ingredients = getIngredients();
        Response responseOrder = sendPostOrderWithAuth(ingredients, token);
        checkCreateOrderWithoutAuth(responseOrder, "Экзо-плантаго бургер");


        // Удаляем пользователя
        Response responseDelete = deleteUser(token);
        checkDeleteUser(responseDelete);
    }

    @Test
    @Feature("Order")
    @Story("Order")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание заказа без ингредиентов")
    public void createOrderWithoutIngredientsTest() {
        Response response = sendPostOrderWithoutIngredients();
        checkCreateOrderWithoutIngredients(response);
    }

    @Test
    @Feature("Order")
    @Story("Order")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание заказа - невалидные хэши ингредиентов")
    public void createOrderIncorrectIngredientsTest() {
        OrderRequest data = getIncorrectIngredients();
        Response response = sendPostOrderWithoutAuth(data);
        checkCreateOrderIncorrectIngredients(response);
    }


    @Test
    @Feature("Order")
    @Story("Order")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение заказов - без авторизации")
    public void getOrderWithoutAuthTest() {
        Response response = sendGetOrderWithoutAuth();
        checkGetOrderWithoutAuth(response);
    }

    @Test
    @Feature("Order")
    @Story("Order")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение заказов - c авторизацией")
    public void getOrderWithAuthTest() {
        CreateUserRequest data = getUser();
        // Создать пользователя
        Response response = sendPostRequestCreateUser(data);
        checkCreateUser(response);

        // Получаем токен
        String token = getAuthToken(response);
        OrderRequest ingredients = getIngredients();
        Response responseOrder = sendPostOrderWithAuth(ingredients, token);
        checkCreateOrderWithoutAuth(responseOrder, "Экзо-плантаго бургер");

        // Получаем заказы
        Response responseGetOrder = sendGetOrderWithAuth(token);
        checkGetOrderWithAuth(responseGetOrder);

        // Удаляем пользователя
        Response responseDelete = deleteUser(token);
        checkDeleteUser(responseDelete);


    }

}
