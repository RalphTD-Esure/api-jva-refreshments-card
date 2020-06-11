package com.project.refreshments.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Accessors(chain = true)
@Table(name = "top_ups")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class TopUpEntity implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "top_up_id")
    private Integer topUpId;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "top_up_date")
    private LocalDateTime topUpDate;

}
