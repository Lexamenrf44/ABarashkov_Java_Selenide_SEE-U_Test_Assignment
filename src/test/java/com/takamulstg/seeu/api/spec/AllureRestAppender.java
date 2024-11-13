package com.takamulstg.seeu.api.spec;

import io.qameta.allure.restassured.AllureRestAssured;

public class AllureRestAppender {

    private static final AllureRestAssured FILTER = new AllureRestAssured();

    public static AllureRestAssured withCustomTemplates() {
        FILTER.setRequestTemplate("request.ftl");
        FILTER.setResponseTemplate("response.ftl");

        return FILTER;
    }
}
