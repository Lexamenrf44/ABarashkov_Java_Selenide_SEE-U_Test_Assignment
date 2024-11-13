package com.takamulstg.seeu.tests.web;

import com.takamulstg.seeu.data.LoginPageAlerts;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("Check authorization functionality with invalid credentials")
@Tag("webTests")
@Epic("Web Tests")
@Feature("Login Page Authorization")
@Story("Login Page")
public class InvalidAuthTests extends WebTestBase {

    @Test
    @DisplayName("Should not authorize when 'Email' and 'Password' fields are empty")
    public void loginButtonIsNotClickableOnAllFieldsEmptyTest() {
        loginPage.loginButtonIsNotClickableAssertion();
    }

    @Test
    @DisplayName("Should display alert message when 'Email' field is empty")
    public void emailFieldIsEmptyAlertTest() {
            loginPage
                    .clickEmailField()
                    .clickPasswordField()
                    .emailFieldCannotBeEmptyAssertion(LoginPageAlerts.EMPTY_EMAIL_ALERT);
    }

    @Test
    @DisplayName("Should display alert message when 'Password' field is empty")
    public void passwordFieldIsEmptyAlertTest() {
            loginPage
                    .clickPasswordField()
                    .clickEmailField()
                    .passwordFieldCannotBeEmptyAssertion(LoginPageAlerts.EMPTY_PASSWORD_ALERT);
    }

    @Test
    @DisplayName("Should display alert message when 'Email' field is set with invalid data")
    public void emailFieldInvalidInputAlertTest() {
        loginPage
                .setEmailFieldWithRandomInput()
                .emailFieldInvalidInputAssertion(LoginPageAlerts.INVALID_EMAIL_ALERT);
    }

    @Test
    @DisplayName("Should not authorize when 'Password' field is empty")
    public void loginButtonIsNotClickableOnPasswordFieldEmptyTest() {
        loginPage
                .setEmailFieldWithRandomEmail()
                .loginButtonIsNotClickableAssertion();
    }

    @Test
    @DisplayName("Should not authorize when 'Password' field is empty")
    public void loginButtonIsNotClickableOnEmailFieldEmptyTest() {
        loginPage
                .setPasswordFieldWithRandomPassword()
                .loginButtonIsNotClickableAssertion();
    }

    @Test
    @DisplayName("Should not authorize when 'Email' and 'Password' fields are set with invalid input")
    public void loginButtonIsNotClickableOnAllFieldsSetWithInvalidInputTest() {
        loginPage
                .setEmailFieldWithRandomInput()
                .setPasswordFieldWithRandomPassword()
                .loginButtonIsNotClickableAssertion();
    }

    @Test
    @DisplayName("Should display alert message when trying to authorize to SEE-U with invalid 'Email' and 'Password'")
    public void checkInvalidUsernameAndPassword() {
            loginPage
                    .setEmailAndPasswordFieldsWithRandomInput()
                    .clickLoginButton()
                    .invalidEmailOrPasswordFieldsAssertion(LoginPageAlerts.INVALID_EMAIL_AND_PASSWORD_ALERT);
    }
}
