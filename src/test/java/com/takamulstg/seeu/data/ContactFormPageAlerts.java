package com.takamulstg.seeu.data;

public enum ContactFormPageAlerts {

    EMPTY_CONTACT_FULL_NAME_ALERT("This input field cannot be empty"),
    CONTACT_FULL_NAME_INPUT_LIMIT_ALERT("Full Name must be between 2 and 100 characters."),
    EMPTY_CONTACT_PRIMARY_EMAIL_ADDRESS_ALERT("This input field cannot be empty"),
    INVALID_CONTACT_PRIMARY_EMAIL_ADDRESS_ALERT("Please enter a valid email address."),
    CONTACT_PRIMARY_EMAIL_ADDRESS_EXISTS_ALERT("A contact already exists with that email address."),
    INVALID_CONTACT_ALTERNATIVE_EMAIL_ADDRESS_ALERT("Please enter a valid email address."),
    CONTACT_ALTERNATIVE_EMAIL_ADDRESS_EXISTS_ALERT(" A contact already exists with that email address."),
    EMPTY_CONTACT_PRIMARY_MOBILE_NUMBER_ALERT("This input field cannot be empty"),
    INVALID_CONTACT_PRIMARY_MOBILE_NUMBER_ALERT("Please enter a valid phone number."),
    CONTACT_PRIMARY_MOBILE_NUMBER_EXISTS_ALERT("This phone number already exists"),
    INVALID_CONTACT_SECONDARY_MOBILE_NUMBER_ALERT("Please enter a valid phone number."),
    CONTACT_SECONDARY_MOBILE_NUMBER_EXISTS_ALERT("This phone number already exists"),
    INVALID_CONTACT_WORK_NUMBER_ALERT("Please enter a valid phone number."),
    CONTACT_WORK_NUMBER_EXISTS_ALERT("This phone number already exists");

    private final String messagePattern;

    ContactFormPageAlerts(String messagePattern) {
        this.messagePattern = messagePattern;
    }

    public String getMessagePattern() {
        return messagePattern;
    }
}
