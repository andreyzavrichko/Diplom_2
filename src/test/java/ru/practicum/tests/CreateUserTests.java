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
import static ru.practicum.data.DataGenerator.*;
import static ru.practicum.steps.AuthApi.getAuthToken;
import static ru.practicum.steps.UserApi.deleteUser;
import static ru.practicum.steps.UserApi.sendPostRequestCreateUser;
@DisplayName("Создание пользователя")
public class CreateUserTests {

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }


    @Test
    @Feature("User")
    @Story("User")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание пользователя")
    public void createUserTest() {
        CreateUserRequest data = getUser();
        // Создать пользователя
        Response response = sendPostRequestCreateUser(data);
        checkCreateUser(response);

        // Удаляем пользователя
        String token = getAuthToken(response);
        Response responseDelete = deleteUser(token);
        checkDeleteUser(responseDelete);

    }

    @Test
    @Feature("User")
    @Story("User")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание пользователя зарегистрированного в системе")
    public void createDuplicateUserTest() {
        CreateUserRequest data = getDuplicateUser();
        // Создать пользователя
        Response response = sendPostRequestCreateUser(data);
        checkCreateDuplicateUser(response);
    }

    @Test
    @Feature("User")
    @Story("User")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание пользователя с пустыми полями")
    public void createUserEmptyFieldsTest() {
        CreateUserRequest data = getEmptyUser();
        // Создать пользователя
        Response response = sendPostRequestCreateUser(data);
        checkCreateRequiredFieldsUser(response);
    }


}
