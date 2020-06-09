package com.project.refreshments.dto;

import com.project.refreshments.model.Item;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PurchaseRequestDto {

    @NotNull
    private String username;
    @NotNull
    private List<Item> itemCodes;

}
