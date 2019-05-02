package com.example.sociallogin.constants;

public class AppConstants {

    public enum SharedPreferenceKeys {
        USER_NAME("userName"),
        USER_EMAIL("userEmail"),
        USER_IMAGE_URL("userImageUrl");


        private String value;

        SharedPreferenceKeys(String value) {
            this.value = value;
        }

        public String getKey() {
            return value;
        }
    }

    public static final String GOOGLE_CLIENT_ID = "986706878451-440n5kd4jkfm9en2o2gcmt0s7mvi8hqn.apps.googleusercontent.com";

}
