package com.project.refreshments.service;

import java.time.LocalDateTime;
import javax.transaction.Transactional;

import com.project.refreshments.dto.RegistrationRequestDto;
import com.project.refreshments.entity.UserEntity;
import com.project.refreshments.exception.UserAlreadyExistsException;
import com.project.refreshments.factory.AuthenticatedUserFactory;
import com.project.refreshments.model.AuthenticatedUser;
import com.project.refreshments.model.AuthenticationRequest;
import com.project.refreshments.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AuthenticatedUserFactory authenticatedUserFactory;

    @Transactional
    public AuthenticatedUser register(RegistrationRequestDto registrationRequest)
    {
        log.debug("Registering user with information: {}", registrationRequest);
        if (userExists(registrationRequest.getEmployeeId()))
        {
            throw new UserAlreadyExistsException(
                    "A user with the Employee ID " + registrationRequest.getEmployeeId() + " already exists.");
        }
        final UserEntity userEntity = userRepository.saveAndFlush(createUserEntity(LocalDateTime.now(), registrationRequest));
        return authenticatedUserFactory.create(userEntity);
    }

    private final boolean userExists(Integer employeeId)
    {
        return userRepository.findByEmployeeId(employeeId).isPresent();
    }

    public UserEntity createUserEntity(final LocalDateTime createdDate,
            final RegistrationRequestDto registrationRequest)
    {
        return new UserEntity().setEmployeeId(registrationRequest.getEmployeeId())
                .setUsername(registrationRequest.getCardId()).setCreationDate(createdDate)
                .setFirstName(registrationRequest.getFirstName())
                .setLastName(registrationRequest.getLastName()).setEmail(registrationRequest.getEmail())
                .setPassword(passwordEncoder.encode(registrationRequest.getPassword())).setPin(passwordEncoder.encode(registrationRequest.getPin())).setCredentialsNonExpired(true);
    }

    public AuthenticatedUser signIn(final AuthenticationRequest authenticationRequest) {
        try {
            final String username = authenticationRequest.getUsername();
            final UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found"));
            if (userEntity.isCredentialsNonExpired()) {
                userEntity.setCredentialsNonExpired(false);
                userRepository.save(userEntity);
                return authenticatedUserFactory.logOut(userEntity);
            }
            else {
                userEntity.setCredentialsNonExpired(true);
                userRepository.save(userEntity);
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authenticationRequest.getPin()));
                return authenticatedUserFactory.create(userEntity);
            }


        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied", e);
        }
    }


}
