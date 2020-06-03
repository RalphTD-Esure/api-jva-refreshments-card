package com.project.refreshments.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Item
{
    String itemName;
    Long itemCode;
    BigDecimal itemPrice;
}
