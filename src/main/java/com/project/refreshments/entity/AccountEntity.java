package com.project.refreshments.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Accessors(chain = true)
@Table(name = "accounts")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AccountEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "username")
    private String username;

    @Column(name = "creation_date", updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "is_active")
    private boolean isActive;


    public AccountEntity addToBalance(BigDecimal amount) {
        balance = balance == null
                ? amount
                : balance.add(amount);
        return this;
    }

    public AccountEntity subtractFromBalance(BigDecimal amount) {
        balance = balance == null
                ? amount
                : balance.subtract(amount);
        return this;
    }

}
