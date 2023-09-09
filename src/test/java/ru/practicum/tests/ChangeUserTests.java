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

import static ru.practicum.Constants.BASE_URL;
import static ru.practicum.checks.UserCheck.*;
import static ru.practicum.data.DataGenerator.getUser;
import static ru.practicum.steps.AuthApi.getAuthToken;
import static ru.practicum.steps.UserApi.*;
@DisplayName("Редактирование пользователя")
public class ChangeUserTests {
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @Feature("User")
    @Story("User")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение данных пользователя с авторизацией")
    public void getUserTest() {
        CreateUserRequest data = getUser();
        // Создать пользователя
        Response response = sendPostRequestCreateUser(data);
        checkCreateUser(response);

        // Получаем токен
        String token = getAuthToken(response);
        sendGetRequestUser(token);

        String email = response.getBody().jsonPath().get("user.email");
        String name = response.getBody().jsonPath().get("user.name");

        checkDataUser(response, email, name);
        // Удаляем пользователя
        Response responseDelete = deleteUser(token);
        checkDeleteUser(responseDelete);
    }

    @Test
    @Feature("User")
    @Story("User")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Редактирование данных пользователя с авторизацией")
    public void changeUserTest() {
        CreateUserRequest data = getUser();
        // Создать пользователя
        Response response = sendPostRequestCreateUser(data);
        checkCreateUser(response);

        // Получаем токен
        String token = getAuthToken(response);
        sendGetRequestUser(token);
        String email = response.getBody().jsonPath().get("user.email");
        String name = response.getBody().jsonPath().get("user.name");
        checkDataUser(response, email, name);

        CreateUserRequest newData = getUser();
        Response newResponse = sendPatchRequestUser(newData, token);
        checkCreateUser(newResponse);

        // Удаляем пользователя
        Response responseDelete = deleteUser(token);
        checkDeleteUser(responseDelete);
    }

    @Test
    @Feature("User")
    @Story("User")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Редактирование данных пользователя без авторизации")
    public void changeUserWithoutAuthTest() {
        CreateUserRequest data = getUser();
        // Создать пользователя
        Response response = sendPostRequestCreateUser(data);
        checkCreateUser(response);

        // Получаем токен
        String token = getAuthToken(response);

        CreateUserRequest newdata = getUser();
        Response newResponse = sendPatchRequestWithoutAuthUser(newdata);
        checkChangeUserWithoutAuthUser(newResponse);

        // Удаляем пользователя
        Response responseDelete = deleteUser(token);
        checkDeleteUser(responseDelete);
    }


}
