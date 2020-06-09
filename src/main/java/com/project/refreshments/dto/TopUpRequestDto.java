package com.project.refreshments.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class TopUpRequestDto
{
    @NotNull
    private BigDecimal amount;

    @NotNull
    private String username;

}
