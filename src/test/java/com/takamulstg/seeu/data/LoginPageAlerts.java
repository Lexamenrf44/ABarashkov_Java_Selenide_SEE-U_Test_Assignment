package com.takamulstg.seeu.data;

public enum LoginPageAlerts {

    EMPTY_EMAIL_ALERT("This input field cannot be empty"),
    EMPTY_PASSWORD_ALERT("This input field cannot be empty"),
    INVALID_EMAIL_AND_PASSWORD_ALERT("Invalid email or password"),
    INVALID_EMAIL_ALERT("Please enter a valid email");

    private final String messagePattern;

    LoginPageAlerts(String messagePattern) {
        this.messagePattern = messagePattern;
    }

    public String getMessagePattern() {
        return messagePattern;
    }

}
