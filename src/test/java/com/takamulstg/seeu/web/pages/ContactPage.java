package com.takamulstg.seeu.web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;


public class ContactPage extends BasePage<ContactPage> {

    private final SelenideElement
            contactPersonalInformationPageTitle = $(withText("Personal Information")),
            fullNameFieldName = $(withText("Full name")).sibling(0);

    @Override
    public ContactPage checkThatPageLoaded() {
        return null;
    }

    @Step("Navigate to 'Personal Information' page")
    public ContactPage waitUntilPersonalInformationPageIsLoaded() {
        contactPersonalInformationPageTitle.should(appear);
        return this;
    }

    @Step("Assert 'Full Name' Field")
    public ContactPage fullNameFieldAssertion(String contactName) {
        fullNameFieldName.shouldHave(text(contactName));
        return this;
    }
}
