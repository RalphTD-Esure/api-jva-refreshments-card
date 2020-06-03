package com.project.refreshments.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AddFunds
{
    Long accountId;
    BigDecimal amount;
}
