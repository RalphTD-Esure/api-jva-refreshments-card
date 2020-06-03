package com.project.refreshments.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class Purchase
{
    Integer accountId;
    List<Item> items;
    BigDecimal totalPrice = calculateTotalPrice(items);

    private BigDecimal calculateTotalPrice(List<Item> items)
    {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Item item : items)
        {
            BigDecimal itemPrice = item.getItemPrice();
            totalPrice.add(itemPrice);
        }
        return totalPrice;
    }
}
