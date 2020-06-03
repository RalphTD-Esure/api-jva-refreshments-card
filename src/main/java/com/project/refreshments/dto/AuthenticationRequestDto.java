package com.project.refreshments.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuthenticationRequestDto implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String pin;
}
