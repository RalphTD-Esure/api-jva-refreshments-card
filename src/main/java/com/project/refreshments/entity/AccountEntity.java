package com.project.refreshments.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Accessors(chain = true)
@Table(name = "accounts")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AccountEntity
{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "account_id", nullable = false, unique = true)
    private Integer accountId;

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "creation_date", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;


    public AccountEntity addToBalance(BigDecimal amount) {
        balance = balance == null
                ? amount
                : balance.add(amount);
        return this;
    }

    public AccountEntity payFromBalance(BigDecimal price) {
        balance = balance == null
                ? price
                : balance.subtract(price);
        return this;
    }

}
