package com.takamulstg.seeu.api;

import java.util.UUID;

public class Endpoint {

    public static class Post {
        public static String getToken() {return "/connect/token";}
        public static String createContact() {return "/api/contact/create-contact";}
        public static String dashboardSearch() {return "api/Dashboard/search";}
        public static String getAllContacts() {return "/api/contact/all-contacts";}
        public static String createContactGroup() {return "/api/contact/contact-list";}
        public static String addContactsToContactGroup() {return "/api/contact/move-selected-contacts";}
        public static String getAllContactsFromContactGroup() {return "/api/contact/contact-list-by-list-id";}
        public static String createCustomField() {return "/api/customField/create";}
        public static String moveContacts() {return "/api/contact/move-contacts";}
        public static String getContactGroup() {return "/api/contact/list-name";}
        public static String getAllCustomFields() {return "/api/customField/get-all";}
    }

    public static class Get {
        public static String getProfile() {
            return "/api/user/myProfile";
        }
        public static String getPersonalInfo(UUID id) {return "/api/contactDetail/personal-info/" + id;}
    }
}
