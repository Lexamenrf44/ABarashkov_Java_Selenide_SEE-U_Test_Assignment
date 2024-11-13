package com.takamulstg.seeu.web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class AllContactsPage extends BasePage<AllContactsPage> {

    private final SelenideElement
            allContactsPageTitle = $(withText("All Contacts"));

    @Override
    public AllContactsPage checkThatPageLoaded() {
        return null;
    }

    @Step("'All Changes Saved' notification display")
    public AllContactsPage waitUntilAllContactsPagesIsLoaded() {
        allContactsPageTitle.should(appear);

        return this;
    }
}
