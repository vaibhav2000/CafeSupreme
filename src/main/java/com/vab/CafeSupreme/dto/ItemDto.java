package com.vab.CafeSupreme.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemDto {
    private String itemName;
    private String itemDescription;
    private BigDecimal itemPrice;
    private BigDecimal itemCalories;
}
