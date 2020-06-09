package com.project.refreshments.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Accessors(chain = true)
@Table(name = "purchases")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PurchaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Integer purchaseId;

    @Column(name = "account_id", nullable = false)
    private long accountId;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "item_id", nullable = false)
    private String itemId;

    @Column(nullable = false)
    private LocalDateTime transactionDate;


}
