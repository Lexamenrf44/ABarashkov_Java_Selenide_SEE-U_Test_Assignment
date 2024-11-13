package com.takamulstg.seeu.web.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.takamulstg.seeu.data.ContactFormPageAlerts;
import com.takamulstg.seeu.utils.RandomDataUtils;
import com.takamulstg.seeu.web.components.CalendarPickerComponent;
import com.takamulstg.seeu.web.components.Month;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;

public class AddContactPage extends BasePage<AddContactPage> {

    private final CalendarPickerComponent calendarPickerComponent = new CalendarPickerComponent();

    private final SelenideElement
            createNewContactPersonalDetailsPageTitle = $(withText("Fill in personal details of the contact")),
            createNewContactHowToReachPageTitle = $(withText("How to reach your contact?")),
            createNewContactCreationFinishPageTitle = $(withText("Congratulations")),
            createNewContactCreationFinishContactNameRow = $(withText("You have successfully added")),

            titleDropdownMenuField = $(withText("Title")).parent().parent().parent().$("mat-select"),
            fullNameInputField = $(withText("Full Name")).parent().parent().parent().$("input"),
            preferredNameInputField = $(withText("Preferred Name")).parent().parent().parent().$("input"),
            maleGenderRadioButton = $(withText("Male")),
            femaleGenderRadioButton = $(withText("Female")),
            nationalityDropdownMenuField = $(withText("Nationality")).parent().parent().parent().$("input"),
            documentTypeDropDownMenuField = $(withText("Document Type")),
            expirationDateOfPassportField = $(withText("ExpirationDate")),
            primaryEmailAddressInputField = $(withText("Email Address")).parent().parent().parent().$("input"),
            alternativeEmailAddressInputField = $(withText("Alternative Email Address")).parent().parent().parent().$("input"),
            primaryMobileNumberInputField = $(withText("Mobile Number")).parent().parent().parent().$("input"),
            secondaryMobileNumberInputField = $(withText("Secondary Mobile Number")).parent().parent().parent().$("input"),

            nextButton = $("[type='submit']"),
            backButton = $(withText("Back")),
            createContactButton = $(withText("Create Contact")),
            viewCreatedContactButton = $(withText("View Contact"));

    private final ElementsCollection
            dropdownMenu = $$(".mat-option-text");

    @Override
    public AddContactPage checkThatPageLoaded() {
        return null;
    }

    @Step("Click field")
    public AddContactPage clickField(SelenideElement field) {
        field.click();

        return this;
    }

    @Step("Click 'Full Name' field")
    public AddContactPage clickFullNameField() {
        fullNameInputField.click();

        return this;
    }

    @Step("Click 'Preferred Name' field")
    public AddContactPage clickPreferredNameField() {
        preferredNameInputField.click();

        return this;
    }

    @Step("Click primary 'Email Address' field")
    public AddContactPage clickPrimaryEmailAddressField() {
        primaryEmailAddressInputField.click();

        return this;
    }

    @Step("Click 'Alternative Email Address' field")
    public AddContactPage clickAlternativeEmailAddressField() {
        alternativeEmailAddressInputField.click();

        return this;
    }

    @Step("Click primary 'Mobile Number' field")
    public AddContactPage clickPrimaryMobileNumberField() {
        primaryMobileNumberInputField.click();

        return this;
    }

    @Step("Click 'Secondary Mobile Number' field")
    public AddContactPage clickSecondaryMobileNumberField() {
        secondaryMobileNumberInputField.click();

        return this;
    }

    @Step("'Fill in personal details of the contact' page display")
    public AddContactPage waitUntilPersonalDetailsPageIsLoaded() {
        createNewContactPersonalDetailsPageTitle.should(appear);
        return this;
    }

    @Step("'How to reach your contact?' page display")
    public AddContactPage waitUntilHowToReachPageIsLoaded() {
        createNewContactHowToReachPageTitle.should(appear);
        return this;
    }

    @Step("Set 'Title' field")
    public AddContactPage setTitle(String title) {
        titleDropdownMenuField.click();
        dropdownMenu.findBy(text(title)).click();

        return this;
    }

    @Step("Set 'Full Name' field")
    public AddContactPage setFullName(String input) {
        fullNameInputField.setValue(input);

        return this;
    }

    @Step("Set 'Full Name' field with random full name")
    public AddContactPage setFullNameWithRandomFullName() {
        fullNameInputField.setValue(RandomDataUtils.randomFullName());
        return this;
    }

    @Step("Set 'Full Name' field with random lorem ipsum")
    public AddContactPage setFullNameWithRandomInputs() {
        fullNameInputField.setValue(RandomDataUtils.randomLoremIpsumLong());
        return this;
    }

    @Step("Set manually 'Full Name' field")
    public AddContactPage setFullNameManually(String fullName) {
        fullNameInputField.setValue(fullName);
        return this;
    }

    @Step("Set manually 'Gender' field")
    public AddContactPage setGender(String gender) {
        switch (gender) {
            case("Male") -> {
                maleGenderRadioButton.click();
                maleGenderRadioButton.parent().parent().shouldHave(cssClass("mat-radio-checked"));
            }
            case ("Female") -> {
                femaleGenderRadioButton.click();
                femaleGenderRadioButton.parent().parent().shouldHave(cssClass("mat-radio-checked"));
            }
        }
        return this;
    }

    @Step("Set 'Document Type' field with with hardcoded input")
    public AddContactPage setDocumentTypeWithPassportNumberInput() {
        documentTypeDropDownMenuField.click(ClickOptions.usingJavaScript());
        dropdownMenu.findBy(text("Passport Number")).click();
        return this;
    }

    @Step("Set manually 'Expiration Date' field")
    public AddContactPage setPassportExpirationDateManually(String year, Month month, String day) {
        expirationDateOfPassportField.doubleClick();
        calendarPickerComponent.chooseDate(year, month, day);
        return this;
    }

    @Step("Set manually 'Nationality' field")
    public AddContactPage setNationalityManually(String nationality) {
        nationalityDropdownMenuField.click();
        dropdownMenu.findBy(text(nationality)).click();
        return this;
    }

    @Step("Set 'Nationality' field with random inputs")
    public AddContactPage setNationalityWithRandomInputs() {
        nationalityDropdownMenuField.click();
        ElementsCollection options = dropdownMenu;
        options.shouldHave(sizeGreaterThan(0));
        int randomIndex = RandomDataUtils.randomNumber(options.size()); ;
        options.get(randomIndex).click();
        nationalityDropdownMenuField.shouldHave(cssClass("ng-dirty"));

        return this;
    }

    @Step("Set primary 'Email Address' field with random email")
    public AddContactPage setPrimaryEmailAddressWithRandomEmail() {
        primaryEmailAddressInputField.setValue(RandomDataUtils.randomEmail());
        return this;
    }
    
    @Step("Set primary 'Email Address' field with random inputs")
    public AddContactPage setPrimaryEmailAddressWithRandomInputs() {
        primaryEmailAddressInputField.setValue(RandomDataUtils.randomFullName());
        
        return this;
    }

    @Step("Set manually primary 'Email Address' field")
    public AddContactPage setPrimaryEmailAddressManually(String email) {
        primaryEmailAddressInputField.setValue(email);

        return this;
    }

    @Step("Set primary 'Mobile Number' field with random phone number")
    public AddContactPage setPrimaryMobileNumberWithRandomPhoneNumber() {
        primaryMobileNumberInputField.setValue(RandomDataUtils.randomPhone());
        return this;
    }

    @Step("Set primary 'Mobile Number' field with random inputs")
    public AddContactPage setPrimaryMobileNumberWithRandomInputs() {
        primaryMobileNumberInputField.setValue(RandomDataUtils.randomFullName());
        return this;
    }

    @Step("Set primary 'Mobile Number' field with existing input")
    public AddContactPage setPrimaryMobileNumberWithExistingInputs(String mobileNumber) {
        primaryMobileNumberInputField.setValue(mobileNumber);
        return this;
    }

    @Step("Click [Next] button")
    public AddContactPage clickNextButton() {
        nextButton
                .shouldBe(visible)
                .shouldBe(enabled)
                .shouldNotHave(cssClass("mat-button-disabled"))
                .click();

        return this;
    }

    @Step("Click [Create Contact] button")
    public AddContactPage clickCreateContactButton() {
        createContactButton
                .shouldBe(visible)
                .shouldBe(enabled)
                .shouldNotHave(cssClass("mat-button-disabled"))
                .click();

        return this;
    }

    @Step("Navigate to the newly created contact page")
    public ContactPage clickViewCreatedContactButton() {
        viewCreatedContactButton.click();
        return new ContactPage().waitUntilPersonalInformationPageIsLoaded();
    }

    @Step("Create manually a new contact")
    public ContactPage manuallyCreateNewContactWithCompulsoryFieldsOnlyViaNewContactPage(String fullName) {
        fullNameInputField.setValue(fullName);
        nextButton.click();
        primaryEmailAddressInputField.setValue(RandomDataUtils.randomEmail());
        primaryMobileNumberInputField.setValue(RandomDataUtils.randomPhone());
        createContactButton.click();
        createNewContactCreationFinishContactNameRow.shouldHave(text(format(" ", fullName)));
        viewCreatedContactButton.click();

        return new ContactPage().waitUntilPersonalInformationPageIsLoaded();
    }

    @Step("Assert 'Full Name' field cannot be empty alert message")
    public AddContactPage fullNameFieldCannotBeEmptyAssertion(ContactFormPageAlerts alert) {
        fullNameInputField
                .parent()
                .parent()
                .parent()
                .$(".mat-error")
                .shouldHave(partialText(alert.getMessagePattern()));

        return this;
    }

    @Step("Assert 'Full Name' field input limits alert message")
    public AddContactPage fullNameFieldInputLimitsAssertion(ContactFormPageAlerts alert) {
        fullNameInputField
                .parent()
                .parent()
                .parent()
                .$(".mat-error")
                .shouldHave(partialText(alert.getMessagePattern()));

        return this;
    }

    @Step("Assert primary 'Email Address' field cannot be empty alert message")
    public AddContactPage primaryEmailAddressCannotBeEmptyAssertion(ContactFormPageAlerts alert) {
        primaryEmailAddressInputField
                .parent()
                .parent()
                .parent()
                .$(".mat-error")
                .shouldHave(partialText(alert.getMessagePattern()));

        return this;
    }

    @Step("Assert primary 'Email Address' field is input with invalid input alert message")
    public AddContactPage primaryEmailAddressInvalidInputAssertion(ContactFormPageAlerts alert) {
        primaryEmailAddressInputField
                .parent()
                .parent()
                .parent()
                .$(".mat-error")
                .shouldHave(partialText(alert.getMessagePattern()));

        return this;
    }

    @Step("Assert primary 'Email Address' input already exists alert message")
    public AddContactPage primaryEmailAddressExistsAssertion(ContactFormPageAlerts alert) {
        primaryEmailAddressInputField
                .parent()
                .parent()
                .parent()
                .$(".mat-error")
                .shouldHave(partialText(alert.getMessagePattern()));

        return this;
    }

    @Step("Assert primary 'Mobile Number' field cannot be empty alert message")
    public AddContactPage primaryMobileNumberCannotBeEmptyAssertion(ContactFormPageAlerts alert) {
        primaryMobileNumberInputField
                .parent()
                .parent()
                .parent()
                .parent()
                .$(".mat-error")
                .shouldHave(partialText(alert.getMessagePattern()));

        return this;
    }

    @Step("Assert primary 'Mobile Number' field is input with invalid input alert message")
    public AddContactPage primaryMobileNumberInvalidInputAssertion(ContactFormPageAlerts alert) {
        primaryMobileNumberInputField
                .parent()
                .parent()
                .parent()
                .parent()
                .$(".mat-error")
                .shouldHave(partialText(alert.getMessagePattern()));

        return this;
    }

    @Step("Assert primary 'Mobile Number' field exists alert message")
    public AddContactPage primaryMobileNumberExistsAssertion(ContactFormPageAlerts alert) {
        primaryMobileNumberInputField
                .parent()
                .parent()
                .parent()
                .parent()
                .$(".mat-error")
                .shouldHave(partialText(alert.getMessagePattern()));

        return this;
    }

    @Step("Assert [Next] button is not clickable")
    public AddContactPage nextButtonIsNotClickableAssertion() {
        nextButton
                .shouldBe(visible)
                .shouldHave(cssClass("mat-button-disabled"))
                .shouldBe(not(enabled));

        return this;
    }

    @Step("Assert [Create Contact] button is not clickable")
    public AddContactPage createContactButtonIsNotClickableAssertion() {
        createContactButton.parent().parent()
                .shouldBe(visible)
                .shouldHave(cssClass("mat-button-disabled"))
                .shouldBe(not(enabled));

        return this;
    }

    @Step("Assert a new contact has been successfully created")
    public AddContactPage waitUntilNewContactCreationFinishPageIsLoaded(String contactName) {
        createNewContactCreationFinishPageTitle.shouldBe(visible);
        $(withText(format("You have successfully added %s", contactName))).should(appear);
        return this;
    }


}
