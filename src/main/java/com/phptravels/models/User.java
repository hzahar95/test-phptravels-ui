package com.phptravels.models;

import com.github.javafaker.Faker;
import lombok.Builder;
import lombok.Getter;

import static com.phptravels.utils.RandomGenerator.generateValidEmail;

@Getter
@Builder
public class User {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String street;
    private String city;
    private String state;
    private String postcode;
    private String password;
    private String confirmPassword;

    public static User generateValidUser() {
        Faker faker = new Faker();
        String randomPassword = "Selenium2022";
        String randomPhoneNumber = "78555666";
        return User.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .emailAddress(generateValidEmail())
                .phoneNumber(randomPhoneNumber)
                .street(faker.address().streetAddress())
                .city(faker.address().cityName())
                .state(faker.address().state())
                .postcode(faker.address().zipCode())
                .password(randomPassword)
                .confirmPassword(randomPassword)
                .build();
    }

}
