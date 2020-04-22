package com.project.refreshments.model;

import java.math.BigInteger;
import java.time.LocalDateTime;

import com.project.refreshments.entity.UserEntity;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {

    private BigInteger userId;
    private String firstName;
    private String lastName;
    private String emailAddress;

    public UserEntity createUserEntity(final LocalDateTime createdDate) {
        return new UserEntity().setId(userId).setBalance(0.0F).setCreationDate(createdDate).setFirstName(firstName)
                .setLastName(lastName).setEmailAddress(emailAddress);
    }
}
