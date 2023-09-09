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
import ru.practicum.models.AuthRequest;

import static ru.practicum.Constants.BASE_URL;
import static ru.practicum.checks.AuthCheck.checkAuthInvalidLoginPass;
import static ru.practicum.checks.AuthCheck.checkAuthUser;
import static ru.practicum.data.DataGenerator.getAuth;
import static ru.practicum.data.DataGenerator.getInvalidAuth;
import static ru.practicum.steps.AuthApi.sendPostRequestAuth;
@DisplayName("Авторизация пользователя")
public class AuthTests {
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }


    @Test
    @Feature("Auth")
    @Story("Auth")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Авторизация пользователя")
    public void AuthValidTest() {
        AuthRequest data = getAuth();
        Response response = sendPostRequestAuth(data);
        checkAuthUser(response);
    }

    @Test
    @Feature("Auth")
    @Story("Auth")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Авторизация пользователя с неверным логином и паролем")
    public void AuthInValidTest() {
        AuthRequest data = getInvalidAuth();
        Response response = sendPostRequestAuth(data);
        checkAuthInvalidLoginPass(response);
    }

}
