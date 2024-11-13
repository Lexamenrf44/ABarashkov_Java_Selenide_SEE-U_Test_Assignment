package com.takamulstg.seeu.web.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class NavbarComponent {
    private final SelenideElement self = $("[name='navbar']");

    private final SelenideElement addContactButton = self.$(withText("Contacts")).sibling(0);

    public NavbarComponent waitUntilNavbarIsLoaded() {
        self.should(appear);

        return this;
    }

    public NavbarComponent clickAddContactButton() {
        addContactButton.click();

        return this;
    }

}
