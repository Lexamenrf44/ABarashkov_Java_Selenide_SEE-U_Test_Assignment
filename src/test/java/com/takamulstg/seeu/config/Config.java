package com.takamulstg.seeu.config;

import java.util.UUID;

public interface Config {

    static Config getInstance() {
        return LocalConfig.INSTANCE;
    }

    String frontBaseUrl();

    String apiBaseUri();

    String dashboardPageUrl();

    String profilePageUrl();

    String legalBaseUrl();

    String privacyPolicyPageUrl();

    String termsAndConditionsPageUrl();

    String newContactPageUrl();

    String newContactGroupPageUrl(UUID contactGroupId);

}
