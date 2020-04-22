package com.project.refreshments.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Accessors(chain = true)
@Table(name = "users")
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserEntity
{

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private BigInteger id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "balance", nullable = false)
    private Float balance;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;


    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + firstName + ", email=" + emailAddress + '}';
    }
}