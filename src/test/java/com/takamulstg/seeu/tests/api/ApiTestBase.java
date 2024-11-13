package com.takamulstg.seeu.tests.api;

import com.github.javafaker.Faker;
import com.takamulstg.seeu.api.RestClient;
import com.takamulstg.seeu.api.spec.Specification;

import static com.takamulstg.seeu.data.Password.valid_password;
import static com.takamulstg.seeu.data.Username.valid_username;

public class ApiTestBase {

    protected static final RestClient restClient = new RestClient();
    protected final Faker faker = new Faker();

    static {
        Specification.installSpecification();
        restClient.getTokenAndSetToStaticContext(valid_username.getEmail(), valid_password.getPassword());
    }
}
