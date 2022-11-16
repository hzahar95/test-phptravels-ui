package com.phptravels.utils;

import com.github.javafaker.Faker;

import static java.util.Objects.nonNull;

public final class FakerUtils {

    private static final Faker FAKER = new Faker();
    private static final String PHONE_NUMBER_REGEX = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";

    public static String generateValidEmail() {
        return "mymail" + System.nanoTime() + "@outlook.com";
    }

    public static String generateRandomFirstName() {
        return FAKER.name().firstName();
    }

    public static String generateRandomLastName() {
        return FAKER.name().lastName();
    }

    public static String generateRandomPhoneNumber() {
        return FAKER.phoneNumber().cellPhone();
    }

    public static String generateRandomCompanyName() {
        return FAKER.company().name();
    }

    public static String generateRandomStreet() {
        return FAKER.address().streetAddress();
    }

    public static String generateRandomAlternativeStreet() {
        return FAKER.address().streetAddress();
    }

    public static String generateRandomCity() {
        return FAKER.address().cityName();
    }

    public static String generateRandomState() {
        return FAKER.address().state();
    }

    public static String generateRandomPostcode() {
        return FAKER.address().zipCode();
    }

    public static String generateRandomMobile() {
        return FAKER.expression("#{regexify '" + PHONE_NUMBER_REGEX + "'}");
    }

    public static String generateStrongPassword() {
        String password = new Faker().internet().password(8, 12, true, false, true);
        if (isPasswordValid(password)) {
            return password;
        }
        return generateStrongPassword();
    }

    private static boolean isPasswordValid(String password) {
        return nonNull(password) && password.length() >= 8 &&
                password.chars().anyMatch(Character::isDigit) &&
                password.chars().anyMatch(Character::isLowerCase) &&
                password.chars().anyMatch(Character::isUpperCase);
    }

}
