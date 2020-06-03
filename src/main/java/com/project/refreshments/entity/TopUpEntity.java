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
@Table(name = "top_ups")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class TopUpEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @Column(nullable = false)
    private Integer accountId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private boolean isToppingUp;

    @Column(nullable = true)
    private String item;

    @Column(nullable = false)
    private LocalDateTime topUpDate;

}
