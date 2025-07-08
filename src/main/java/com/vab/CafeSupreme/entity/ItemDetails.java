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
    private Integer rating;

    @OneToMany(mappedBy = "itemDetails")
    private List<OrderDetails> orderDetailsList;
}
