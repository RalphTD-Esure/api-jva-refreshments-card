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
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "creation_date", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;

    @OneToOne
    @MapsId
    private UserEntity user;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;



    public AccountEntity addToBalance(BigDecimal amount) {
        balance = balance == null
                ? amount
                : balance.add(amount);
        return this;
    }

}
