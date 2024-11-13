package com.takamulstg.seeu.tests.api;

import com.takamulstg.seeu.api.dtos.AllContactsJson;
import com.takamulstg.seeu.api.dtos.ContactJson;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static com.takamulstg.seeu.data.Username.valid_username;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Check API calls functionality")
@Tag("restTests")
@Epic("Rest Tests")
@Feature("Rest Architecture")
public class ApiCallsTests extends ApiTestBase {

    @Test
    @DisplayName("Get profile endpoint test")
    public void getProfileTest() {
        var profile = restClient.getProfile();

        assertThat(profile.getEmail()).isEqualTo(valid_username.getEmail());
        assertThat(profile.getFirstName()).isEqualTo("Candidates");
        assertThat(profile.getIsBtoC()).isTrue();
        assertThat(profile.getLastName()).isEqualTo("Accounr");
        assertThat(profile.getPhoneNo()).isEqualTo("+966555255525");
        assertThat(profile.getRole()).isEqualTo(1);
    }

    @Test
    @DisplayName("Create new contact test")
    public void createContactTest() {
        var newContact = ContactJson.builder()
                .email(faker.name().firstName() + "@gmail.com")
                .fullName(faker.name().fullName())
                .mobileNumber(faker.phoneNumber().phoneNumber())
                .build();

        UUID createdId = restClient.createNewContact(newContact);

        var createdContact = restClient.getPersonalInfo(createdId);
        assertThat(newContact.getFullName()).isEqualTo(createdContact.getFullName());
        List<AllContactsJson> allContacts = restClient.getAllContacts();
        assertThat(allContacts.get(0).getFullName()).isEqualTo(createdContact.getFullName());
    }

    @Test
    @DisplayName("Create new contact group and add contacts in it")
    public void createContactGroupTest() {
        var newContactGroupId = restClient.createNewContactGroup();
        var newContact = ContactJson.builder()
                .email(faker.name().firstName() + "@gmail.com")
                .fullName(faker.name().fullName())
                .mobileNumber(faker.phoneNumber().phoneNumber())
                .build();

        UUID contactId = restClient.createNewContact(newContact);
        restClient.addContactsToContactGroup(List.of(contactId), newContactGroupId);
        List<AllContactsJson> allContactsFromContactGroup = restClient.getAllContactsFromContactGroup(newContactGroupId);
        assertThat(allContactsFromContactGroup.get(0).getFullName()).isEqualTo(newContact.getFullName());
    }

    @Test
    @DisplayName("Search contact by name test")
    public void searchContactByNameTest() {
        var newContact = ContactJson.builder()
                .email(faker.name().firstName() + "@gmail.com")
                .fullName(faker.name().fullName())
                .mobileNumber(faker.phoneNumber().phoneNumber())
                .build();

        restClient.createNewContact(newContact);

        var foundContact = restClient.searchContactByName(newContact.getFullName())
                .stream()
                .filter(c -> c.getTitle().equals(newContact.getFullName()))
                .findFirst().orElseThrow();

        assertThat(foundContact.getTitle()).isEqualTo(newContact.getFullName());
    }
}
