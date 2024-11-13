package com.takamulstg.seeu.web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

public class LegalPage extends BasePage<LegalPage> {

    private final SelenideElement
            privacyPolicyPageTitle = $("[id='privacy-policy']"),
            termsAndConditionsPageTitle = $("[id='terms-and-conditions']");

    @Override
    public LegalPage checkThatPageLoaded() {
        return null;
    }

    @Step("Display 'Privacy Policy' page title")
    public LegalPage waitUntilPrivacyPolicyPageIsLoaded() {
        privacyPolicyPageTitle.should(appear);
        return this;
    }

    @Step("Display 'Terms and Conditions' page title")
    public LegalPage waitUntilTermsAndConditionsPageIsLoaded() {
        termsAndConditionsPageTitle.should(appear);
        return this;
    }
}
