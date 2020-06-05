package com.project.refreshments.dto;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AuthenticationRequestDto
{
    @Size(min=16)
    private String username;
    private String password;
    private String pin;
}
