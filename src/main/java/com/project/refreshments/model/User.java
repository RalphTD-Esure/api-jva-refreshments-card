package com.project.refreshments.model;

import com.project.refreshments.entity.UsersCreation;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class User {

    private BigInteger userId;
    private String firstName;
    private String lastName;
    private String emailAddress;

    public UsersCreation toUsersTable(final LocalDateTime createdDate) {
        return new UsersCreation().setId(userId).setBalance(0.0F).setCreationDate(createdDate).setFirstName(firstName)
                .setLastName(lastName).setEmailAddress(emailAddress);
    }

}
