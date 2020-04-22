package com.project.refreshments.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Table;

@Entity
@Accessors(chain = true)
@Data
@Table(name = "account")
public class AccountEntity
{
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private BigInteger accountId;

    @Column(name = 'person_id', nullable = false, unique = true)
    private long personId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

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
