package com.takamulstg.seeu.tests.web;

import com.takamulstg.seeu.api.dtos.ContactJson;
import com.takamulstg.seeu.data.ContactGroupPageAlerts;
import com.takamulstg.seeu.data.CustomFieldType;
import com.takamulstg.seeu.jupiter.AddContactGroup;
import com.takamulstg.seeu.jupiter.ApiLogin;
import com.takamulstg.seeu.jupiter.CreateCustomField;
import com.takamulstg.seeu.utils.RandomDataUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Isolated;

import java.util.List;
import java.util.UUID;

import static com.codeborne.selenide.Selenide.*;
import static com.takamulstg.seeu.jupiter.UUIDParameter.ContactGroupUUID;
import static com.takamulstg.seeu.jupiter.UUIDParameter.FieldUUID;

@Isolated
@DisplayName("Check contact group, public form with custom fields creation functionality")
@Tag("webTests")
@Epic("Web Tests")
@Feature("Contact Group Creation")
@Story("Contact Group Page")
public class CreateContactGroupTests extends WebTestBase {

    @ApiLogin
    @Test
    @DisplayName("Should create a new contact group")
    public void createNewContactGroupTest() {
        dashboardPage
                .navigateToDashboardPage()
                .createNewContactGroup()
                .assertAllChangesSavedNotification();
    }

    @ApiLogin
    @AddContactGroup(cleanUp = false)
    @Test
    @DisplayName("Should delete created contact group")
    public void deleteNewContactGroupTest(UUID contactGroupId) {
        open(config.newContactGroupPageUrl(contactGroupId));
        contactGroupPage.deleteContactGroup("Delete Contact Group");
        open(config.newContactGroupPageUrl(contactGroupId));
        contactGroupPage.contactGroupCannotBeFoundByIdAssertion(ContactGroupPageAlerts.CONTACT_GROUP_NOT_FOUND_ALERT);
    }

    @ApiLogin
    @AddContactGroup(cleanUp = false)
    @Test
    @DisplayName("Should create new contact group with contacts")
    public void createNewContactGroupWithContactsTest(UUID contactGroupId) {
        open(config.newContactGroupPageUrl(contactGroupId));

        String randomFullname = RandomDataUtils.randomFullName();

        var newContact = ContactJson.builder()
                .email(RandomDataUtils.randomEmail())
                .fullName(randomFullname)
                .mobileNumber(RandomDataUtils.randomPhone())
                .build();

        UUID contactId = restClient.createNewContact(newContact);
        restClient.addContactsToContactGroup(List.of(contactId), contactGroupId);

        refresh();

        contactGroupPage.addedContactAppearedInContactGroup(randomFullname);
    }

    @ApiLogin
    @AddContactGroup
    @Test
    @DisplayName("Should add date type question with custom date field to public form and save form")
    public void addDateTypeQuestionWithCustomCreatedDateFieldToPublicFormTest(UUID contactGroupId) {
        open(config.newContactGroupPageUrl(contactGroupId));
        contactGroupPage
                .openPublicForm()
                .addAdditionalFormToPublicFormFromDropdownMenu("Date")
                .createNewDateCustomField()
                .savePublicForm();
    }

    @ApiLogin
    @AddContactGroup
    @Test
    @DisplayName("Should add select type question with custom select field to public form and save form")
    public void addSelectTypeQuestionWithCustomCreatedSelectFieldToPublicFormTest(UUID contactGroupId) {
        open(config.newContactGroupPageUrl(contactGroupId));
        sleep(60000);
        contactGroupPage
                .openPublicForm()
                .addAdditionalFormToPublicFormFromDropdownMenu("Select")
                .createNewSelectCustomFieldWithOptions()
                .savePublicForm();
    }

    @ApiLogin
    @AddContactGroup
    @Test
    @DisplayName("Should add rating type question with custom created rating field to public form and save form")
    public void addRatingTypeQuestionWithCustomCreatedRatingFieldToPublicFormTest(UUID contactGroupId) {
        open(config.newContactGroupPageUrl(contactGroupId));
        contactGroupPage
                .openPublicForm()
                .addAdditionalFormToPublicFormFromDropdownMenu("Rating")
                .createNewRatingCustomField()
                .savePublicForm();
    }

    @ApiLogin
    @AddContactGroup
    @Test
    @DisplayName("Should add multiple questions with custom created fields to public form and save form")
    public void addMultipleQuestionsWithCustomCreatedFieldsToPublicFormTest(UUID contactGroupId) {
        open(config.newContactGroupPageUrl(contactGroupId));
        contactGroupPage
                .openPublicForm()
                .addAdditionalFormToPublicFormFromDropdownMenu("Date")
                .createNewDateCustomField()
                .addAdditionalFormToPublicFormFromDropdownMenu("Select")
                .createNewSelectCustomFieldWithOptions()
                .savePublicForm();
    }

    @ApiLogin
    @AddContactGroup
    @CreateCustomField(type = CustomFieldType.date)
    @Test
    @DisplayName("Should add date type question with API created date field selected from search field to public form and save form")
    public void addDateTypeQuestionWithApiCreatedDateFieldTest(@ContactGroupUUID UUID contactGroupId, @FieldUUID UUID fieldId) {
        open(config.newContactGroupPageUrl(contactGroupId));
        contactGroupPage
                .openPublicForm()
                .addAdditionalFormToPublicFormFromDropdownMenu("Date")
                .selectCustomFieldFromSearch(restClient.getCustomFieldById(fieldId).getName())
                .savePublicForm();
    }

    @ApiLogin
    @AddContactGroup
    @CreateCustomField(type = CustomFieldType.text)
    @Test
    @DisplayName("Should add select type question with API created select field selected from search field to public form and save form")
    public void addSelectTypeQuestionWithApiCreatedSelectFieldTest(@ContactGroupUUID UUID contactGroupId, @FieldUUID UUID fieldId) {
        open(config.newContactGroupPageUrl(contactGroupId));
        contactGroupPage
                .openPublicForm()
                .addAdditionalFormToPublicFormFromDropdownMenu("Select")
                .selectCustomFieldFromSearch(restClient.getCustomFieldById(fieldId).getName())
                .addSelectOptionsToSelectForm()
                .savePublicForm();
    }

    @ApiLogin
    @AddContactGroup
    @CreateCustomField(type = CustomFieldType.rating)
    @Test
    @DisplayName("Should add rating type question with API created rating field selected from search field to public form and save form")
    public void addRatingTypeQuestionWithApiCreatedRatingFieldTest(@ContactGroupUUID UUID contactGroupId, @FieldUUID UUID fieldId) {
        open(config.newContactGroupPageUrl(contactGroupId));
        contactGroupPage
                .openPublicForm()
                .addAdditionalFormToPublicFormFromDropdownMenu("Rating")
                .selectCustomFieldFromSearch(restClient.getCustomFieldById(fieldId).getName())
                .savePublicForm();
    }
}
