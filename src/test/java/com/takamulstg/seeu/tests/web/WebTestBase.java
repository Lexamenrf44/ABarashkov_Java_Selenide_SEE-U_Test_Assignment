package com.takamulstg.seeu.tests.web;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.takamulstg.seeu.api.RestClient;
import com.takamulstg.seeu.api.spec.Specification;
import com.takamulstg.seeu.config.Config;
import com.takamulstg.seeu.jupiter.BrowserExtension;
import com.takamulstg.seeu.web.pages.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(BrowserExtension.class)
public class WebTestBase {

    protected final LoginPage loginPage = new LoginPage();
    protected final DashboardPage dashboardPage = new DashboardPage();
    protected final AllContactsPage allContactsPage = new AllContactsPage();
    protected final AddContactPage addContactPage = new AddContactPage();
    protected final ContactPage contactPage = new ContactPage();
    protected final ContactGroupPage contactGroupPage = new ContactGroupPage();
    protected final LegalPage legalPage = new LegalPage();
    protected final RestClient restClient = new RestClient();

    protected static final Config config = Config.getInstance();

    static {
        Configuration.baseUrl = config.frontBaseUrl();
        Configuration.browser = Browsers.FIREFOX;
        Configuration.browserSize = "1440x900";
        Configuration.timeout = 10000;
        Configuration.headless = true;

        Specification.installSpecification();
    }
}
