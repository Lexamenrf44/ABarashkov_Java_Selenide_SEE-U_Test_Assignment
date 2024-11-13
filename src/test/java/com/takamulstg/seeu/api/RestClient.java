package com.takamulstg.seeu.api;

import com.takamulstg.seeu.api.dtos.*;
import com.takamulstg.seeu.data.CustomFieldType;
import com.takamulstg.seeu.utils.RandomDataUtils;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RestClient {

    @Step("Create custom field")
    public UUID createCustomField(CustomFieldType fieldType) {
        switch (fieldType) {
            case date:
                return createNewDateTypeCustomField();
            case text:
                return createNewSelectTypeCustomField();
            case rating:
                return createNewRatingTypeCustomField();
            default:
                throw new IllegalArgumentException("Unsupported CustomFieldType: " + fieldType);
        }
    }

    @Step("Get profile data")
    public ProfileJson getProfile() {
        return RestService.getAsDto(Endpoint.Get.getProfile(), ProfileJson.class);
    }

    @Step("Get user's token")
    public void getTokenAndSetToStaticContext(String username, String password) {
        Response response = RestAssured.given()
                .multiPart("username", username)
                .multiPart("password", password)
                .multiPart("grant_type", "password")
                .multiPart("client_id", "client-spa")
                .multiPart("client_secret", "secret")
                .multiPart("portal", "admin")
                .contentType(ContentType.MULTIPART)
                .post(Endpoint.Post.getToken())
                .then()
                .extract().response();

        RestContext.accessToken = response.jsonPath().getString("access_token");
        RestContext.refreshToken = response.jsonPath().getString("refresh_token");
    }

    @Step("Post create new contact")
    public UUID createNewContact(ContactJson contact) {
        return RestService.postAsResponse(Endpoint.Post.createContact(), contact).as(UUID.class);
    }

    @Step("Post get all contacts")
    public List<AllContactsJson> getAllContacts() {
        return RestService.postAsListItems(
                Endpoint.Post.getAllContacts(),
                Map.of("pageNo",1, "pageSize", 15),
                AllContactsJson.class
        );
    }

    @Step("Post get contact group")
    public ContactGroupJson getContactGroup(UUID id) {
        return RestService.postAsDto(Endpoint.Post.getContactGroup(), Map.of("id", id), ContactGroupJson.class);
    }

    @Step("Post get all contacts in contact group")
    public List<AllContactsJson> getAllContactsFromContactGroup(UUID contactGroupId) {
        return RestService.postAsListItems(
                Endpoint.Post.getAllContactsFromContactGroup(),
                Map.of("listId", contactGroupId, "pageNo",1, "pageSize", 15),
                AllContactsJson.class
        );
    }

    @Step("Get profile data")
    public ContactJson getPersonalInfo(UUID id) {
        return RestService.getAsDto(Endpoint.Get.getPersonalInfo(id), ContactJson.class);
    }

    @Step("Post search contact by name")
    public List<ContactSearchJson> searchContactByName(String name) {
        return RestService.postAsListItems(
                Endpoint.Post.dashboardSearch(),
                Map.of("searchText", name),
                ContactSearchJson.class
        );
    }

    @Step("Post create new contact group")
    public UUID createNewContactGroup() {
        ContactGroupJson contactGroup = ContactGroupJson.builder()
                .color(RandomDataUtils.randomColor())
                .name(RandomDataUtils.randomSurname())
                .build();

        return RestService.postAsResponse(Endpoint.Post.createContactGroup(), contactGroup).as(UUID.class);
    }

    @Step("Post create new contact group")
    public void addContactsToContactGroup(List<UUID> contactsIds, UUID to) {
        ContactsToGroupJson contactsToGroupJson = ContactsToGroupJson
                .builder()
                .contactsIds(contactsIds)
                .to(to)
                .build();

        RestService.postAsResponse(Endpoint.Post.addContactsToContactGroup(), contactsToGroupJson);
    }

    @Step("Post delete contact group")
    public void deleteContactGroup(UUID id) {
        RestService.postAsResponse(Endpoint.Post.moveContacts(), Map.of("from", id ));
    }

    @Step("Post create new date type custom field")
    public UUID createNewDateTypeCustomField() {
        Map<String, Object> body = new HashMap<>();
        body.put("customFieldType", CustomFieldType.date);
        body.put("name", RandomDataUtils.randomCustomDateFieldName());
        body.put("minValue", 1722510563000L);
        body.put("maxValue", 1732968573000L);
        return RestService.postAsResponse(Endpoint.Post.createCustomField(), body).as(UUID.class);
    }

    @Step("Post create new select type custom field")
    public UUID createNewSelectTypeCustomField() {
        Map<String, Object> body = new HashMap<>();
        body.put("customFieldType", CustomFieldType.text);
        body.put("name", RandomDataUtils.randomCustomSelectFieldName());
        body.put("minValue", 10);
        body.put("maxValue", 100);
        return RestService.postAsResponse(Endpoint.Post.createCustomField(), body).as(UUID.class);
    }

    @Step("Post create new rating type custom field")
    public UUID createNewRatingTypeCustomField() {
        Map<String, Object> body = new HashMap<>();
        body.put("customFieldType", CustomFieldType.rating);
        body.put("name", RandomDataUtils.randomCustomRatingFieldName());
        body.put("minValue", null);
        body.put("maxValue", null);
        return RestService.postAsResponse(Endpoint.Post.createCustomField(), body).as(UUID.class);
    }

    @Step("Post get custom field by Id")
    public GetAllCustomFieldsJson getCustomFieldById(UUID id) {
        Map<String, List<String>> body = new HashMap<>();
        body.put("fields", List.of(id.toString()));
        return RestService.postAsList(Endpoint.Post.getAllCustomFields(), body, GetAllCustomFieldsJson.class).get(0);
    }
}
