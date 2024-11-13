package com.takamulstg.seeu.utils;

import com.github.javafaker.Faker;

public class RandomDataUtils {

    private static final Faker faker = new Faker();


    public static String randomUsername() {
        return faker.name().username();
    }

    public static String randomFullName() {
        return faker.name().fullName();
    }

    public static String randomLoremIpsumLong() {
        return faker.lorem().characters(101);
    }

    public static String randomSurname() {
        return faker.name().lastName();
    }

    public static String randomEmail() {
        return faker.name().firstName() + "@gmail.com";
    }

    public static String randomPassword() {
        return faker.name().firstName() + "@123";
    }

    public static String randomPhone() {
        return "+7 " + "953" + " " + faker.number().digits(3) + " " + faker.number().digits(2) + " " + faker.number().digits(2);
    }

    public static int randomNumber(int max) {
        return faker.number().numberBetween(0, max);
    }

    public static String randomColor() {
        return faker.color().hex();
    }

    public static String randomCustomDateFieldName() {
        return faker.lorem().word() + " " + "Date Type Custom Field" + " " + faker.number().digits(3);
    }

    public static String randomCustomSelectFieldName() {
        return faker.lorem().word() + " " + "Select Type Custom Field" + " " + faker.number().digits(3);
    }

    public static String randomSelectOptionFieldName() {
        return faker.lorem().word() + " " + "Option";
    }

    public static String randomCustomRatingFieldName() {
        return faker.lorem().word() + " " + "Rating Type Custom Field" + " " + faker.number().digits(3);
    }
}
