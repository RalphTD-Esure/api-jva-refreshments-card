package com.project.refreshments.factory;

import com.project.refreshments.entity.UserEntity;
import com.project.refreshments.model.AuthenticatedUser;
import com.project.refreshments.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUserFactory {
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthenticatedUserFactory(final JwtProvider jwtProvider)
    {
        this.jwtProvider = jwtProvider;
    }

    public AuthenticatedUser create(final UserEntity userEntity) {
        return new AuthenticatedUser()
                .setUsername(userEntity.getUsername())
                .setToken(jwtProvider.createToken(userEntity.getUsername(), userEntity.getRoles()));
    }

    public AuthenticatedUser logOut(final UserEntity userEntity) {
        return new AuthenticatedUser()
                .setUsername(userEntity.getUsername())
                .goodbyeMessage();
    }
}