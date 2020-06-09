package com.project.refreshments.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Accessors(chain = true)
@Table(name = "stock")
@Data
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "item_price", nullable = false)
    private BigDecimal itemPrice;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}
