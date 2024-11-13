package com.takamulstg.seeu.tests.web;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.proxy.CaptureType;
import com.browserup.harreader.model.HarEntry;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.takamulstg.seeu.api.Endpoint;
import com.takamulstg.seeu.api.dtos.ContactJson;
import com.takamulstg.seeu.jupiter.ApiLogin;
import com.takamulstg.seeu.utils.RandomDataUtils;
import com.takamulstg.seeu.web.components.Month;
import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Isolated;

import java.util.List;
import java.util.UUID;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.assertj.core.api.Assertions.assertThat;

@Isolated
@DisplayName("Check console logs")
@Tag("webTests")
@Epic("Web Tests")
@Feature("Console Logs")
public class ConsoleNetworkTests extends WebTestBase {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void setUp() {
        Configuration.proxyEnabled = true;
    }

    @AfterAll
    public static void tearDown() {
        Configuration.proxyEnabled = false;
    }

    @Test
    @ApiLogin
    @DisplayName("Parse console response after creating new contact")
    void parseNetworkResponseAfterCreatingContactTest() throws JsonProcessingException {

        String username = RandomDataUtils.randomUsername();

        open(config.dashboardPageUrl());

        BrowserUpProxy bmp = WebDriverRunner.getSelenideProxy().getProxy();
        bmp.setHarCaptureTypes(CaptureType.getAllContentCaptureTypes());
        bmp.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        bmp.newHar("rnd");

        dashboardPage.navigateToCreateNewContactPage();
        addContactPage
                .setTitle("Mr.")
                .setFullNameManually(username)
                .setGender("Male")
                .setNationalityWithRandomInputs()
                .setDocumentTypeWithPassportNumberInput()
                .setPassportExpirationDateManually("2020", Month.May, "6")
                .clickNextButton()
                .setPrimaryEmailAddressWithRandomEmail()
                .setPrimaryMobileNumberWithRandomPhoneNumber()
                .clickCreateContactButton();

        List<HarEntry> requests = bmp.getHar().getLog().getEntries();
        UUID contactId = null;

        for (HarEntry request : requests) {
            if (request.getRequest().getUrl().contains(Endpoint.Post.createContact()) &&
                    request.getRequest().getMethod().toString().equals("POST")) {

                while (request.getResponse().getContent().getText().isEmpty()) {
                    sleep(200);
                }

                JsonNode jsonNode = objectMapper.readTree(request.getResponse().getContent().getText());
                contactId = UUID.fromString(jsonNode.asText());
                break;
            }
        }
        ContactJson contactJson = restClient.getPersonalInfo(contactId);
        assertThat(contactJson.getFullName()).isEqualTo(username);
    }

}
