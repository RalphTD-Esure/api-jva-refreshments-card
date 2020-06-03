package com.project.refreshments.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Accessors(chain = true)
@Table(name = "purchases")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class PurchaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    @Column(nullable = false)
    private long accountId;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String itemId;

    @Column(nullable = false)
    private LocalDateTime transactionDate;
}
