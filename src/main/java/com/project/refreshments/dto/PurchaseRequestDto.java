package com.project.refreshments.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PurchaseRequestDto {

    @NotNull
    private String username;
    @NotNull
    private Integer itemCode;

}
