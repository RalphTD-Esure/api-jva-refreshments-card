package com.project.refreshments.dto;

import com.project.refreshments.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequestDto
{
    @NotNull
    @Max(999999)
    private Integer employeeId;

    @NotNull
    @Size(min = 16, max = 16)
    @Pattern(regexp="^[a-zA-Z0-9]{16}$")
    private String cardId;

    @NotNull
    @Size(min = 1)
    private String firstName;

    @NotNull
    @Size(min = 1)
    private String lastName;

    @NotNull
    @ValidEmail
    private String email;

    @NotNull
    private String mobile;

    @NotNull
    private String pin;

    @NotNull
    private String confirmPin;
}
