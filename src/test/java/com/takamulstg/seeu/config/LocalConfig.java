package com.takamulstg.seeu.config;

import java.util.UUID;

enum LocalConfig implements Config {
    INSTANCE;

    @Override
    public String frontBaseUrl() {
        return "https://seeu-clientqa.takamulstg.com/";
    }

    @Override
    public String legalBaseUrl() {
        return "https://stage.see-u.net/";
    }

    @Override
    public String privacyPolicyPageUrl() {
        return "privacy-policy";
    }

    @Override
    public String termsAndConditionsPageUrl() {
        return "terms-and-conditions";
    }

    @Override
    public String dashboardPageUrl() {
        return "dashboard";
    }

    @Override
    public String newContactPageUrl() {
        return "contacts/add-contact";
    }

    @Override
    public String newContactGroupPageUrl(UUID contactGroupId) {
        return "contacts/"+contactGroupId;
    }

    @Override
    public String apiBaseUri() {
        return "https://seeu-apiqa.takamulstg.com";
    }

    @Override
    public String profilePageUrl() {
        return "profile/account";
    }
}
