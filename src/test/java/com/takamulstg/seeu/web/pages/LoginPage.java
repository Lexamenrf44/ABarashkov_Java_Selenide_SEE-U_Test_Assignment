package com.takamulstg.seeu.web.pages;

import com.codeborne.selenide.SelenideElement;
import com.takamulstg.seeu.config.Config;
import com.takamulstg.seeu.data.LoginPageAlerts;
import com.takamulstg.seeu.data.Password;
import com.takamulstg.seeu.data.Username;
import com.takamulstg.seeu.utils.RandomDataUtils;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage<LoginPage> {

    private static final Config config = Config.getInstance();

    private final SelenideElement
            loginFormTitle = $(withText("LOGIN TO YOUR ACCOUNT")),

            privacyHrefButton = $(withText("Privacy")),
            termsOfServicesHrefButton = $(withText("Terms of services")),
            securityHrefButton = $(withText("Security")),
            loginButton = $("[type='Submit']"),

            emailField = $("[type='email']"),
            passwordField = $("[type='password']"),

            emailFieldAlertMessage = emailField.parent().parent().parent().$(".mat-error"),
            passwordFieldAlertMessage = passwordField.parent().parent().parent().$(".mat-error"),
            authorizationAlertMessage = $(".mat-error");

    @Override
    public LoginPage checkThatPageLoaded() {
        return null;
    }

    @Step("Click 'Email' field")
    public LoginPage clickEmailField() {
        emailField.click();
        return this;
    }

    @Step("Click 'Password' field")
    public LoginPage clickPasswordField() {
        passwordField.click();
        return this;
    }

    @Step("Click [Login] button")
    public LoginPage clickLoginButton() {
        clickButton(loginButton);
        return this;
    }

    @Step("Set 'Email' field with random input")
    public LoginPage setEmailFieldWithRandomInput() {
        emailField.setValue(RandomDataUtils.randomFullName());
        return this;
    }

    @Step("Set 'Email' field with random email")
    public LoginPage setEmailFieldWithRandomEmail() {
        emailField.setValue(RandomDataUtils.randomEmail());
        return this;
    }

    @Step("Set 'Password' field with random password")
    public LoginPage setPasswordFieldWithRandomPassword() {
        passwordField.setValue(RandomDataUtils.randomPassword());
        return this;
    }

    @Step("Authorize manually via login page")
    public DashboardPage manuallyAuthorizeViaLoginPage(Username username, Password password) {
        loginFormTitle.should(appear);
        buttonDisabledAssertion(loginButton);
        emailField.setValue(username.getEmail());
        passwordField.setValue(password.getPassword());
        clickButton(loginButton);

        return new DashboardPage().waitUntilDashboardPageIsLoadedAssertion();
    }

    @Step("Set 'Email' and 'Password' fields with random input")
    public LoginPage setEmailAndPasswordFieldsWithRandomInput() {
        emailField.setValue(RandomDataUtils.randomEmail());
        passwordField.setValue(RandomDataUtils.randomPassword());

        return this;
    }

    @Step("Navigate to 'Privacy Policy' page via link from login page footer")
    public LegalPage navigateToPrivacyLegalPage() {
        privacyHrefButton.click();
        switchAndAssertPageUrl(config.legalBaseUrl(), config.privacyPolicyPageUrl());

        return new LegalPage().waitUntilPrivacyPolicyPageIsLoaded();
    }

    @Step("Navigate to 'Terms and Conditions' page via link from login page footer")
    public LegalPage navigateToTermsOfServicesLegalPage() {
        termsOfServicesHrefButton.click();
        switchAndAssertPageUrl(config.legalBaseUrl(), config.termsAndConditionsPageUrl());

        return new LegalPage().waitUntilTermsAndConditionsPageIsLoaded();
    }

    @Step("Navigate to 'Privacy Policy' page via link from login page footer")
    public LegalPage navigateToSecurityLegalPage() {
        securityHrefButton.click();
        switchAndAssertPageUrl(config.legalBaseUrl(), config.privacyPolicyPageUrl());

        return new LegalPage().waitUntilPrivacyPolicyPageIsLoaded();
    }

    @Step("Assert 'Email' field cannot be empty alert message")
    public LoginPage emailFieldCannotBeEmptyAssertion(LoginPageAlerts alert) {
        emailFieldAlertMessage.shouldHave(partialText(alert.getMessagePattern()));
        return this;
    }

    @Step("Assert 'Password' field cannot be empty alert message")
    public LoginPage passwordFieldCannotBeEmptyAssertion(LoginPageAlerts alert) {
        passwordFieldAlertMessage.shouldHave(partialText(alert.getMessagePattern()));
        return this;
    }

    @Step("Assert 'Email' field is input with invalid input alert message")
    public LoginPage emailFieldInvalidInputAssertion(LoginPageAlerts alert) {
        emailFieldAlertMessage.shouldHave(partialText(alert.getMessagePattern()));
        return this;
    }

    @Step("Assert [Login] button is not clickable")
    public LoginPage loginButtonIsNotClickableAssertion() {
        buttonDisabledAssertion(loginButton);
        return this;
    }

    @Step("Assert invalid email or password alert message")
    public LoginPage invalidEmailOrPasswordFieldsAssertion(LoginPageAlerts alert) {
        authorizationAlertMessage
                .should(appear)
                .shouldHave(text(alert.getMessagePattern()));
        return this;
    }
}
