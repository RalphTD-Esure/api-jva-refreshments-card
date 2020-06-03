package com.project.refreshments.entity;

import javax.persistence.*;

import com.project.refreshments.config.AuthorityType;
import lombok.Data;

@Entity
@Data
@Table(name = "authority")
public class AuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private AuthorityType name;

    public AuthorityEntity() {}

    public AuthorityEntity(AuthorityType name) {
        this.name = name;
    }
}
