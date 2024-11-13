package com.takamulstg.seeu.tests.web;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("Check Link Buttons in Footer")
@Tag("webTests")
@Epic("Web Tests")
@Feature("Link Buttons in Footer")
@Story("Login Page")
public class LoginPageFooterLinksNavigationTests extends WebTestBase {

    @Test
    @DisplayName("Should navigate to Privacy Policy page")
    public void checkPrivacyLinkFooterTest() {
        loginPage.navigateToPrivacyLegalPage();
    }

    @Test
    @DisplayName("Should navigate to Terms and Conditions page ")
    public void checkTermsOfSecurityLinkFooterTest() {
        loginPage.navigateToTermsOfServicesLegalPage();
    }

    @Test
    @DisplayName("Should navigate to Privacy Policy page ")
    public void checkSecurityLinkFooterTest() {
        loginPage.navigateToSecurityLegalPage();
    }
}
