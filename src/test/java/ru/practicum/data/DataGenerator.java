package ru.practicum.data;

import com.github.javafaker.Faker;
import ru.practicum.models.AuthRequest;
import ru.practicum.models.CreateUserRequest;
import ru.practicum.models.OrderRequest;

import java.util.ArrayList;

import static ru.practicum.steps.UserApi.sendPostRequestCreateUser;

public class DataGenerator {


    public static CreateUserRequest getUser() {
        Faker faker = new Faker();
        return CreateUserRequest.builder()
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .name(faker.name().firstName())
                .build();
    }

    public static CreateUserRequest getDuplicateUser() {
        return CreateUserRequest.builder()
                .email("andrey@yandex.ru")
                .password("password")
                .name("andrey")
                .build();
    }

    public static CreateUserRequest getEmptyUser() {
        return CreateUserRequest.builder()
                .email("")
                .password("")
                .name("")
                .build();
    }


    public static AuthRequest getAuth() {
        CreateUserRequest data = getUser();
        // Создать пользователя
        sendPostRequestCreateUser(data);

        return AuthRequest.builder()
                .email(data.getEmail())
                .password(data.getPassword())
                .build();
    }


    public static AuthRequest getInvalidAuth() {

        return AuthRequest.builder()
                .email("dsfsdfsdfdsf@dsgfdsgdsg.er")
                .password("dsfdsfsdfdsf")
                .build();
    }


    public static OrderRequest getIngredients() {

        ArrayList<String> list = new ArrayList<>();
        list.add("61c0c5a71d1f82001bdaaa79");
        list.add("609646e4dc916e00276b2870");

        return OrderRequest.builder()
                .ingredients(list)
                .build();
    }

    public static OrderRequest getIncorrectIngredients() {

        ArrayList<String> list = new ArrayList<>();
        list.add("61c0c5a71d1f88001bdaaa79");
        list.add("609646e4dc916e20276b2870");

        return OrderRequest.builder()
                .ingredients(list)
                .build();
    }

}
