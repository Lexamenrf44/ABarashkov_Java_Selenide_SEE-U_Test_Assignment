package com.takamulstg.seeu.web.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.takamulstg.seeu.data.ContactGroupPageAlerts;
import com.takamulstg.seeu.utils.RandomDataUtils;
import com.takamulstg.seeu.web.components.CalendarPickerComponent;
import com.takamulstg.seeu.web.components.Month;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class ContactGroupPage extends BasePage<ContactGroupPage>{

    private final SelenideElement
            contactGroupDeleteDialog = $("[role='dialog']"),
            publicRegistrationForm = $(withText("Public Registration Form")),

            dropdownMenu = $("[role='menu']"),

            customFieldName = $(withText("Create New Custom Field")).parent().$(withText("Name")).parent().parent().parent().$("input"),
            calendarMinDateField = $("[data-placeholder='Min date']"),
            calendarMaxDateField = $("[data-placeholder='Max date']"),
            selectTypeMinLengthField = $("[placeholder='Min length']"),
            selectTypeMaxLengthField = $("[placeholder='Max length']"),
            listItem = $(".list-item"),

            dropdownMenuSearchField = $("[data-placeholder='Search...']"),
            dropdownMenuTypeForCustomField = $("#type"),
            dropdownMenuTypeOptionForCustomField = $(".mat-option-text"),

            contactGroupMoreOptionsVerticalButton = $(withText("more_vert")),
            contactGroupDeleteDialogConfirmButton = $(withText("Confirm")),
            publicFormButton = $(withText("ballot")),
            fullNameFormDropdownMenuButton = $(withText("text_format")).parent().parent().parent().parent().$(withText("add")),
            addCustomFieldButton = $(withText("Add custom field...")),
            calendarSetButton = $("owl-date-time-container").$(withText("Set")),
            customFieldAddButton = $x("//button[.//text()[contains(., 'Add')]]"),
            selectTypeAddOptionButton = $x("//*[contains(text(), 'add_circle_outline')]"),
            savePublicFormButton = $x("//button[.//text()[contains(., 'Save Form')]]");

    private final ElementsCollection
            formNameField = $$("[placeholder='Type Question here']"),
            dropdownMenuWithSearch = $$(".mat-select-placeholder"),
            selectTypeOptionNameField = $$("[placeholder='Type Option']");

    @Override
    public ContactGroupPage checkThatPageLoaded() {
        return this;
    }

    @Step("'All Changes Saved' notification display")
    public ContactGroupPage waitUntilAllChangesSavedNotificationLoaded() {
        assertAllChangesSavedNotification();
        publicFormButton.shouldBe(visible);

        return this;
    }

    @Step("Delete Contact Group")
    public AllContactsPage deleteContactGroup(String option) {
        contactGroupMoreOptionsVerticalButton.click();
        dropdownMenu.$(withText(option)).click();
        contactGroupDeleteDialog.should(appear);
        contactGroupDeleteDialogConfirmButton.click();
        contactGroupDeleteDialog.should(disappear);

        return new AllContactsPage().waitUntilAllContactsPagesIsLoaded();
    }

    @Step("Open 'Public Form'")
    public ContactGroupPage openPublicForm() {
        publicFormButton.click();
        publicRegistrationForm.should(appear);

        return this;
    }

    @Step("Add additional form to public from dropdown menu")
    public ContactGroupPage addAdditionalFormToPublicFormFromDropdownMenu(String option) {
        sleep(1000);
        scrollViaJsIntoElement(fullNameFormDropdownMenuButton).hover().click();
        dropdownMenu.$(withText(option)).click();
        formNameField.get(1).setValue(RandomDataUtils.randomSurname());

        return this;
    }

    @Step("Create new 'Date' custom field")
    public ContactGroupPage createNewDateCustomField() {
        dropdownMenuWithSearch.get(1).doubleClick();
        addCustomFieldButton.click();
        scrollViaJsIntoElement(dropdownMenuTypeForCustomField).click();
        dropdownMenuTypeOptionForCustomField.click();
        customFieldName.setValue(RandomDataUtils.randomCustomDateFieldName());
        calendarMinDateField.click();
        new CalendarPickerComponent().chooseDate("2020", Month.May, "10");
        calendarSetButton.click();
        calendarMaxDateField.click();
        new CalendarPickerComponent().chooseDate("2025", Month.May, "10");
        calendarSetButton.click();
        customFieldAddButton.click();

        return this;
    }

    @Step("Create new 'Select' custom field with options")
    public ContactGroupPage createNewSelectCustomFieldWithOptions() {
        dropdownMenuWithSearch.get(1).doubleClick();
        addCustomFieldButton.click();
        scrollViaJsIntoElement(dropdownMenuTypeForCustomField).click();
        dropdownMenuTypeOptionForCustomField.click();
        customFieldName.setValue(RandomDataUtils.randomCustomSelectFieldName());
        selectTypeMinLengthField.setValue("5");
        selectTypeMaxLengthField.setValue("100");
        customFieldAddButton.click();
        selectTypeAddOptionButton.click();
        Selenide.sleep(1500);
        scrollViaJsIntoElement(selectTypeAddOptionButton).hover().click();
        selectTypeOptionNameField.forEach(x -> x.setValue(RandomDataUtils.randomSurname()));

        return this;
    }

    @Step("Add 'Select' options to form")
    public ContactGroupPage addSelectOptionsToSelectForm() {
        Selenide.sleep(1500);
        selectTypeAddOptionButton.click();
        scrollViaJsIntoElement(selectTypeAddOptionButton).hover().click();
        selectTypeOptionNameField.forEach(x -> x.setValue(RandomDataUtils.randomSelectOptionFieldName()));

        return this;
    }

    @Step("Create new 'Rating' custom field")
    public ContactGroupPage createNewRatingCustomField() {
        dropdownMenuWithSearch.get(1).doubleClick();
        addCustomFieldButton.click();
        scrollViaJsIntoElement(dropdownMenuTypeForCustomField).click();
        dropdownMenuTypeOptionForCustomField.click();
        customFieldName.setValue(RandomDataUtils.randomCustomRatingFieldName());
        customFieldAddButton.click();

        return this;
    }

    @Step("Select Field Type From Search")
    public ContactGroupPage selectCustomFieldFromSearch(String option) {
        dropdownMenuWithSearch.get(1).doubleClick();
        dropdownMenuSearchField.setValue(option);
        $(withText(option)).click();

        return this;
    }

    @Step("Save Public")
    public ContactGroupPage savePublicForm() {
        savePublicFormButton.click();
        assertAllChangesSavedNotification();

        return this;
    }

    @Step("Assert contact group can't be found by id alert message")
    public ContactGroupPage contactGroupCannotBeFoundByIdAssertion(ContactGroupPageAlerts alert) {
        $(withText(alert.getMessagePattern())).should(appear);

        return this;
    }

    @Step("Assert that added contact appeared in contact group")
    public ContactGroupPage addedContactAppearedInContactGroup(String fullName) {
        listItem.$(withText(fullName)).should(appear);

        return this;
    }
}
