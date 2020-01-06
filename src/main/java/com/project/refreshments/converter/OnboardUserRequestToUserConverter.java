package com.project.refreshments.converter;

import com.project.api.refreshments.swagger.model.OnboardUserRequest;
import com.project.refreshments.model.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
@RequiredArgsConstructor
public class OnboardUserRequestToUserConverter implements Converter<User, OnboardUserRequest> {

    public User convert(final @NonNull OnboardUserRequest request) {
        return new User().setUserId(BigInteger.valueOf(request.getUserId())).setFirstName(request.getFirstName())
                .setLastName(request.getLastName()).setEmailAddress(request.getEmailAddress());
    }
}
