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

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

}
