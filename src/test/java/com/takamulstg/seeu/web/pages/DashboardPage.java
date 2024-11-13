package com.takamulstg.seeu.web.pages;

import com.codeborne.selenide.SelenideElement;
import com.takamulstg.seeu.config.Config;
import com.takamulstg.seeu.web.components.NavbarComponent;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DashboardPage extends BasePage<DashboardPage> {

    private static final Config config = Config.getInstance();

    private final NavbarComponent navbarcomponent = new NavbarComponent();

    private final SelenideElement
            createNewContactGroupButton = $(withText("groups")),
            createNewContactButton = $(withText("person_add")),
            dashboardTitle = $("[class^='logo-text']");

    @Override
    public DashboardPage checkThatPageLoaded() {
        return null;
    }

    @Step("Create 'New Contact Group'")
    public ContactGroupPage createNewContactGroup() {
        navbarcomponent.clickAddContactButton();
        createNewContactGroupButton.click();

        return new ContactGroupPage().waitUntilAllChangesSavedNotificationLoaded();
    }

    @Step("Open 'Create New Contact' page")
    public AddContactPage openNewContactForm() {
        navbarcomponent.clickAddContactButton();
        createNewContactButton.click();

        return new AddContactPage().waitUntilPersonalDetailsPageIsLoaded();
    }

    @Step("Navigate to 'Dashboard' page")
    public DashboardPage navigateToDashboardPage() {
        open(config.dashboardPageUrl());
        return this;
    }

    @Step("Navigate to 'Create New Contact' page")
    public AddContactPage navigateToCreateNewContactPage() {
        open(config.newContactPageUrl());
        return new AddContactPage().waitUntilPersonalDetailsPageIsLoaded();
    }

    @Step("'Dashboard' page display")
    public DashboardPage waitUntilDashboardPageIsLoadedAssertion() {
        dashboardTitle.should(appear);
        assertPageUrl(config.frontBaseUrl(), config.dashboardPageUrl());

        return this;
    }
}
