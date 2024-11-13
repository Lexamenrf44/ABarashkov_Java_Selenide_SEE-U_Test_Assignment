package com.takamulstg.seeu.jupiter;

import com.codeborne.selenide.Selenide;
import com.takamulstg.seeu.api.RestClient;
import com.takamulstg.seeu.api.RestContext;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;

import java.util.Objects;
import java.util.Optional;

public class ApiLoginExtension implements BeforeEachCallback {

    private final RestClient restClient = new RestClient();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Optional<ApiLogin> annotation = AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), ApiLogin.class);

        if (annotation.isPresent()) {
            ApiLogin apiLogin = annotation.get();

            if (Objects.equals(null, RestContext.accessToken)) {
                restClient.getTokenAndSetToStaticContext(
                        apiLogin.username().getEmail(),
                        apiLogin.password().getPassword()
                );
            }

            Selenide.sessionStorage().setItem("auth_token", RestContext.accessToken);
            Selenide.sessionStorage().setItem("refresh_token", RestContext.refreshToken);
        }
    }
}