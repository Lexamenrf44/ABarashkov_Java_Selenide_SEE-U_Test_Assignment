package com.takamulstg.seeu.tests.web;

import com.codeborne.selenide.WebDriverRunner;
import com.takamulstg.seeu.api.dtos.ContactJson;
import com.takamulstg.seeu.data.ContactFormPageAlerts;
import com.takamulstg.seeu.jupiter.ApiLogin;
import com.takamulstg.seeu.utils.RandomDataUtils;
import com.takamulstg.seeu.web.components.Month;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Isolated;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@Isolated
@DisplayName("Check a new contact creation / existing contact management functionality")
@Tag("webTests")
@Epic("Web Tests")
@Feature("Contacts")
@Story("New Contact")
public class CreateContactTests extends WebTestBase {

    @ApiLogin()
    @Test
    @DisplayName("Should not click on [Next] button when 'Full Name' compulsory field is empty")
    public void nextButtonIsNotClickableOnFullNameFieldEmptyTest() {
        dashboardPage.navigateToCreateNewContactPage();
        addContactPage.nextButtonIsNotClickableAssertion();
    }

    @ApiLogin
    @Test
    @DisplayName("Should display alert message when 'Full Name' field is empty")
    public void fullNameFieldIsEmptyAlertTest() {
        dashboardPage.navigateToCreateNewContactPage();
        addContactPage
                .clickFullNameField()
                .clickPreferredNameField()
                .fullNameFieldCannotBeEmptyAssertion(ContactFormPageAlerts.EMPTY_CONTACT_FULL_NAME_ALERT);
    }

    @ApiLogin
    @Test
    @DisplayName("Should not click on [Create Contact] button when primary 'Email Address' and primary 'Mobile Number' compulsory fields are empty")
    public void createContactButtonIsNotClickableOnAllFieldsEmptyTest() {
        dashboardPage.navigateToCreateNewContactPage();
        addContactPage
                .setFullNameWithRandomFullName()
                .clickNextButton()
                .clickPrimaryEmailAddressField()
                .clickPrimaryMobileNumberField()
                .clickSecondaryMobileNumberField()
                .createContactButtonIsNotClickableAssertion();
    }

    @ApiLogin
    @Test
    @DisplayName("Should display alert message when primary 'Email Address' field is empty")
    public void primaryEmailAddressFieldIsEmptyAlertTest() {
        dashboardPage.navigateToCreateNewContactPage();
        addContactPage
                .setFullNameWithRandomFullName()
                .clickNextButton()
                .clickPrimaryEmailAddressField()
                .clickAlternativeEmailAddressField()
                .primaryEmailAddressCannotBeEmptyAssertion(ContactFormPageAlerts.EMPTY_CONTACT_PRIMARY_EMAIL_ADDRESS_ALERT);
    }

    @ApiLogin
    @Test
    @DisplayName("Should not click on [Create Contact] button when primary 'Mobile Number' compulsory field is empty")
    public void createContactButtonIsNotClickableOnPrimaryMobileNumberFieldsEmptyTest() {
        dashboardPage.navigateToCreateNewContactPage();
        addContactPage
                .setFullNameWithRandomFullName()
                .clickNextButton()
                .setPrimaryEmailAddressWithRandomEmail()
                .clickPrimaryMobileNumberField()
                .clickSecondaryMobileNumberField()
                .createContactButtonIsNotClickableAssertion();
    }

    @ApiLogin
    @Test
    @DisplayName("Should display alert message when primary 'Mobile Number' field is empty")
    public void primaryMobileNumberFieldIsEmptyAlertTest() {
        dashboardPage.navigateToCreateNewContactPage();
        addContactPage
                .setFullNameWithRandomFullName()
                .clickNextButton()
                .clickPrimaryMobileNumberField()
                .clickAlternativeEmailAddressField()
                .primaryMobileNumberCannotBeEmptyAssertion(ContactFormPageAlerts.EMPTY_CONTACT_PRIMARY_MOBILE_NUMBER_ALERT);
    }

    @ApiLogin
    @Test
    @DisplayName("Should click on [Create Contact] button when primary 'Email Address' compulsory field is empty")
    public void createContactButtonIsNotClickableOnPrimaryEmailAddressFieldsEmptyTest() {
        dashboardPage.navigateToCreateNewContactPage();
        addContactPage
                .setFullNameWithRandomFullName()
                .clickNextButton()
                .clickPrimaryEmailAddressField()
                .clickAlternativeEmailAddressField()
                .createContactButtonIsNotClickableAssertion();
    }

    @ApiLogin
    @Test
    @DisplayName("Should display alert message when 'Full Name' field input limit is full")
    public void fullNameFieldInputLimitAlertTest() {
        dashboardPage.navigateToCreateNewContactPage();
        addContactPage
                .setFullNameWithRandomInputs()
                .clickPreferredNameField()
                .fullNameFieldInputLimitsAssertion(ContactFormPageAlerts.CONTACT_FULL_NAME_INPUT_LIMIT_ALERT);
    }

    @ApiLogin
    @Test
    @DisplayName("Should display alert message when primary 'Email Address' field is set with invalid input")
    public void primaryEmailAddressFieldInvalidInputAlertTest() {
        dashboardPage.navigateToCreateNewContactPage();
        addContactPage
                .setFullNameWithRandomFullName()
                .clickNextButton()
                .setPrimaryEmailAddressWithRandomInputs()
                .clickAlternativeEmailAddressField()
                .primaryEmailAddressInvalidInputAssertion(ContactFormPageAlerts.INVALID_CONTACT_PRIMARY_EMAIL_ADDRESS_ALERT);
    }

    @ApiLogin
    @Test
    @DisplayName("Should display alert message when primary 'Email Address' already exists")
    public void primaryEmailAddressExistAlertTest() {
        dashboardPage.navigateToCreateNewContactPage();
        addContactPage
                .setFullNameWithRandomFullName()
                .clickNextButton()
                .setPrimaryEmailAddressManually("1_a@a.com")
                .clickPrimaryMobileNumberField()
                .primaryEmailAddressExistsAssertion(ContactFormPageAlerts.CONTACT_PRIMARY_EMAIL_ADDRESS_EXISTS_ALERT);
    }

    @ApiLogin
    @Test
    @DisplayName("Should display alert message when primary 'Mobile Number' field is set with invalid input")
    public void primaryMobileNumberFieldInvalidInputAlertTest() {
        dashboardPage.navigateToCreateNewContactPage();
        addContactPage
                .setFullNameWithRandomFullName()
                .clickNextButton()
                .setPrimaryMobileNumberWithRandomInputs()
                .clickAlternativeEmailAddressField()
                .primaryMobileNumberInvalidInputAssertion(ContactFormPageAlerts.INVALID_CONTACT_PRIMARY_MOBILE_NUMBER_ALERT);
    }

    @ApiLogin
    @Test
    @DisplayName("Should display alert message when primary 'Mobile Number' already exists")
    public void primaryMobileNumberExistsAlertTest() {
        dashboardPage.navigateToCreateNewContactPage();
        addContactPage
                .setFullNameWithRandomFullName()
                .clickNextButton()
                .setPrimaryMobileNumberWithExistingInputs("+79536661122")
                .clickAlternativeEmailAddressField()
                .primaryMobileNumberExistsAssertion(ContactFormPageAlerts.CONTACT_PRIMARY_MOBILE_NUMBER_EXISTS_ALERT);
    }

    @ApiLogin
    @Test
    @DisplayName("Should create a new contact with compulsory fields only")
    public void manualContactCreationWithCompulsoryFieldsTest() {

        String randomFullName = RandomDataUtils.randomFullName();

        dashboardPage.navigateToCreateNewContactPage();
        addContactPage.manuallyCreateNewContactWithCompulsoryFieldsOnlyViaNewContactPage(randomFullName);
        contactPage.fullNameFieldAssertion(randomFullName);

        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Matcher matcher = Pattern.compile("/([a-f0-9\\-]+)/personal-info").matcher(currentUrl);
        String idAsString = null;

        if (matcher.find()) {
            idAsString = matcher.group(1);
        }

        UUID id = UUID.fromString(idAsString);

        ContactJson contactJson = restClient.getPersonalInfo(id);

        assertThat(contactJson.getFullName()).isEqualTo(randomFullName);
    }

    @ApiLogin
    @Test
    @DisplayName("Should create a new with additional fields contact")
    public void manualContactCreationWithAdditionalFieldsTest() {

        String randomFullName = RandomDataUtils.randomFullName();

        dashboardPage.navigateToCreateNewContactPage();
        addContactPage
                .setTitle("Mr.")
                .setFullNameManually(randomFullName)
                .setGender("Male")
                .setNationalityWithRandomInputs()
                .setDocumentTypeWithPassportNumberInput()
                .setPassportExpirationDateManually("2020", Month.May, "6")
                .clickNextButton()
                .setPrimaryEmailAddressWithRandomEmail()
                .setPrimaryMobileNumberWithRandomPhoneNumber()
                .clickCreateContactButton()
                .clickViewCreatedContactButton();
        contactPage.fullNameFieldAssertion(randomFullName);

        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Matcher matcher = Pattern.compile("/([a-f0-9\\-]+)/personal-info").matcher(currentUrl);
        String idAsString = null;

        if (matcher.find()) {
            idAsString = matcher.group(1);
        }

        UUID id = UUID.fromString(idAsString);

        ContactJson contactJson = restClient.getPersonalInfo(id);

        assertThat(contactJson.getFullName()).isEqualTo(randomFullName);
    }
}
