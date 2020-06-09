package com.project.refreshments.factory;

import com.project.refreshments.entity.UserEntity;
import com.project.refreshments.model.AuthenticatedUser;
import com.project.refreshments.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class AuthenticatedUserFactory {
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthenticatedUserFactory(final JwtTokenProvider jwtTokenProvider)
    {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public AuthenticatedUser create(final UserEntity userEntity) {
        return new AuthenticatedUser()
                .setUsername(userEntity.getUsername())
                .setToken(jwtTokenProvider.createToken(userEntity.getUsername(), userEntity.getRoles()))
                .welcomeMessage();
    }

    public AuthenticatedUser logOut(final UserEntity userEntity) {
        return new AuthenticatedUser()
                .setUsername(userEntity.getUsername())
                .goodbyeMessage();
    }
}