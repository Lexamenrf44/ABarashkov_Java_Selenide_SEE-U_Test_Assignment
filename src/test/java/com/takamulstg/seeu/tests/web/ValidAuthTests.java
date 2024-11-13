package com.takamulstg.seeu.tests.web;

import com.takamulstg.seeu.data.Password;
import com.takamulstg.seeu.data.Username;
import com.takamulstg.seeu.jupiter.ApiLogin;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("Check authorization functionality with valid credentials")
@Tag("webTests")
@Epic("Web Tests")
@Feature("Login Page Authorization")
@Story("Login Page")
public class ValidAuthTests extends WebTestBase {

    @Test
    @DisplayName("Should authorize with valid 'Email' and 'Password' on [Login] via login page")
    public void manualAuthorizationWithValidCredentialsTest() {
        loginPage.manuallyAuthorizeViaLoginPage(Username.valid_username, Password.valid_password);
    }

    @ApiLogin
    @Test
    @DisplayName("Should authorize with valid credentials via API-call")
    public void apiAuthorizationWithValidCredentialsTest() {
        dashboardPage
                .navigateToDashboardPage()
                .waitUntilDashboardPageIsLoadedAssertion();
    }
}
