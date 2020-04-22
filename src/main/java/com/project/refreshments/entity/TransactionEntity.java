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
@Table(name = "transaction")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class TransactionEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    private long accountId;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean isToppingUp;

    @Column(nullable = true)
    private String item;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @Column(nullable = true)
    private String location;
}
