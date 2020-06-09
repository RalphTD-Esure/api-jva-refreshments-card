package com.project.refreshments.model;

import lombok.experimental.Accessors;

import java.util.HashMap;

@Accessors(chain = true)
public class AuthenticatedUser extends HashMap<String, String>
{

    private static final long serialVersionUID = 1L;
    private static final String USERNAME = "username";
    private static final String TOKEN = "token";
    private static final String MESSAGE = "message";

    public AuthenticatedUser setUsername(final String username) {
        this.put(USERNAME, username);
        return this;
    }

    public AuthenticatedUser setToken(final String token) {
        this.put(TOKEN, token);
        return this;
    }

    public String getUsername() {
        return this.get(USERNAME);
    }

    public String getToken() {
        return this.get(TOKEN);
    }

    public AuthenticatedUser addMessage(String message) {
        this.put(MESSAGE, message);
        return this;
    }
}
