package com.takamulstg.seeu.data;

public enum ContactGroupPageAlerts {

    CONTACT_GROUP_NOT_FOUND_ALERT("Can't find group by id");

    private final String messagePattern;

    ContactGroupPageAlerts(String messagePattern) {
        this.messagePattern = messagePattern;
    }

    public String getMessagePattern() {
        return messagePattern;
    }
}
