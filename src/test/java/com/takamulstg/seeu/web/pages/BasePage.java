package com.takamulstg.seeu.web.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.takamulstg.seeu.config.Config;
import io.qameta.allure.Step;

import javax.annotation.Nonnull;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverConditions.url;

public abstract class BasePage <T extends BasePage<?>> {

    protected static final Config config = Config.getInstance();

    private final SelenideElement
            allChangesSavedNotification = $(withText("All Changes Saved")),
            dropdownMenu = $("[role='menu']");

    @Step("Page is loaded")
    public abstract T checkThatPageLoaded();

    @SuppressWarnings("unchecked")
    @Nonnull
    @Step("Click Field")
    public T clickField(SelenideElement selector) {
        selector.click();
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    @Step("Click Button")
    public T clickButton(SelenideElement selector) {
        selector
                .shouldBe(visible)
                .shouldBe(enabled)
                .shouldNotHave(cssClass("mat-button-disabled"))
                .click();
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    @Step("Button Disabled")
    public T buttonDisabledAssertion(SelenideElement selector) {
        selector
                .shouldBe(visible)
                .shouldBe(not(enabled))
                .shouldHave(cssClass("mat-button-disabled"));
        return (T) this;
    }

    @Step("Switch and Assert Page Url")
    public void switchAndAssertPageUrl(String url1, String url2) {
        Selenide.switchTo().window(1);
        Selenide.webdriver().shouldHave(url(url1+url2));
    }

    @Step("Assert Page Url")
    public void assertPageUrl(String baseHost, String endpoint) {
        Selenide.webdriver().shouldHave(url(baseHost+endpoint));
    }

    @Step("Assert all changes saved notification")
    public void assertAllChangesSavedNotification() {
        allChangesSavedNotification.should(appear);
    }

    @Step("Scroll into element via js inside container")
    public SelenideElement scrollViaJsIntoElement(SelenideElement element) {
        executeJavaScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'center'})", element);
        return element;
    }
}
