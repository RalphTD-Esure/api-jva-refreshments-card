package com.project.refreshments.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class AuthenticationRequestDto
{
    @NotNull
    @Size(min = 16, max = 16)
    @Pattern(regexp="^[a-zA-Z0-9]{16}$")
    private String username;

    private String pin;
}
