package com.project.refreshments.model;

import java.util.HashMap;

import lombok.experimental.Accessors;

@Accessors(chain = true)
public class AuthenticatedUser extends HashMap<String, String>
{

    private static final long serialVersionUID = 1L;
    private static final String USERNAME = "username";
    private static final String TOKEN = "token";
    private static final String MESSAGE = "message";

    public AuthenticatedUser setUserName(final String username) {
        this.put(USERNAME, username);
        return this;
    }

    public AuthenticatedUser setToken(final String token) {
        this.put(TOKEN, token);
        return this;
    }

    public String getUserName() {
        return this.get(USERNAME);
    }

    public String getToken() {
        return this.get(TOKEN);
    }

    public AuthenticatedUser welcomeMessage() {
        String welcome = "Welcome, please use your token to now access the top up and pay endpoints.";
        this.put(MESSAGE, welcome);
        return this;
    }

    public AuthenticatedUser goodbyeMessage() {
        String goodbye = "Goodbye!";
        this.put(MESSAGE, goodbye);
        return this;
    }

    public AuthenticatedUser addMessage(String message) {
        this.put(MESSAGE, message);
        return this;
    }
}
