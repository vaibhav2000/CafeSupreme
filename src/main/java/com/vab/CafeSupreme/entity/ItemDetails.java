package com.vab.CafeSupreme.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "item_details")
@Getter
@Setter
public class ItemDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;
    private String itemName;
    private String itemDescription;
    private BigDecimal itemPrice;
    private Boolean itemEnabled;
    private BigDecimal itemCalories;
    private Double rating;
	private Integer ratingCounter;

    @OneToMany(mappedBy = "itemDetails")
    private List<OrderDetails> orderDetailsList;

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

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

	public Boolean getItemEnabled() {
		return itemEnabled;
	}

	public void setItemEnabled(Boolean itemEnabled) {
		this.itemEnabled = itemEnabled;
	}

	public BigDecimal getItemCalories() {
		return itemCalories;
	}

	public void setItemCalories(BigDecimal itemCalories) {
		this.itemCalories = itemCalories;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public Integer getRatingCounter() {
		return ratingCounter;
	}

	public void setRatingCounter(Integer ratingCounter) {
		this.ratingCounter = ratingCounter;
	}

	public List<OrderDetails> getOrderDetailsList() {
		return orderDetailsList;
	}

	public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}
    
    
}