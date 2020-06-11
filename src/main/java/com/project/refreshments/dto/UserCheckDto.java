package com.project.refreshments.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserCheckDto
{
    @NotNull
    private String cardId;
}
