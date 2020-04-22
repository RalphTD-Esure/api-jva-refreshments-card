package com.project.refreshments.service;

import java.time.LocalDateTime;
import javax.transaction.Transactional;

import com.project.api.refreshments.swagger.model.OnboardUserRequest;
import com.project.refreshments.converter.OnboardUserRequestToUserConverter;
import com.project.refreshments.entity.UserEntity;
import com.project.refreshments.model.User;
import com.project.refreshments.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OnboardUserService
{
    private final UserRepository userRepository;

    private final OnboardUserRequestToUserConverter converter;

    @Transactional
    public UserEntity saveUserToDatastore(OnboardUserRequest onboardUserRequest){
        final User user = converter.convert(onboardUserRequest);
        return userRepository.save(user.createUserEntity(LocalDateTime.now()));
    }

}
