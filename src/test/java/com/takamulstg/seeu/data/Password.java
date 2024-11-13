package com.takamulstg.seeu.data;

public enum Password {

    valid_password("Test@123");

    private final String password;

    Password(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
