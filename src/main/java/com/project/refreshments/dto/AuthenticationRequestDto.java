package com.project.refreshments.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AuthenticationRequestDto
{
    @NotNull
    @Pattern(regexp="^[a-zA-Z0-9]{16}$")
    private String username;

    @NotNull
    @Pattern(regexp="^[0-9]{4}$")
    private String pin;
}
