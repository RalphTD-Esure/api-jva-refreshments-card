package com.project.refreshments.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDto
{
    private String username;
    private String pin;
}
