package com.project.refreshments.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.project.refreshments.validation.ValidEmail;
import lombok.Data;

@Data
public class RegistrationRequestDto
{
//    @JsonProperty("employeeId")
    @NotNull
    @Max(999999)
    private Integer employeeId;

//    @JsonProperty("cardId")
    @NotNull
    @Size(min = 16, max = 16)
    private String cardId;

//    @JsonProperty("firstName")
    @NotNull
    @Size(min = 1)
    private String firstName;

//    @JsonProperty("lastName")
    @NotNull
    @Size(min = 1)
    private String lastName;

//    @JsonProperty("email")
    @NotNull
    @ValidEmail
    private String email;

//    @JsonProperty("password")
    private String password;

//    @JsonProperty("matchingPassword")
    private String matchingPassword;

//    @JsonProperty("pin")
    private String pin;
}
