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
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public BigDecimal getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}
	public BigDecimal getItemCalories() {
		return itemCalories;
	}
	public void setItemCalories(BigDecimal itemCalories) {
		this.itemCalories = itemCalories;
	}
    
}
