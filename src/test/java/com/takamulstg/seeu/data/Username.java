package com.takamulstg.seeu.data;

public enum Username {

    valid_username("candidates@mailinator.com");

    private final String email;

    Username(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
