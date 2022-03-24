package hu.bme.aut.programsch.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class LoginUrl {
    private final String url;

    public LoginUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
