package com.project.refreshments.service;

import com.project.refreshments.dto.AuthenticationRequestDto;
import com.project.refreshments.dto.RegistrationRequestDto;
import com.project.refreshments.entity.AccountEntity;
import com.project.refreshments.entity.UserEntity;
import com.project.refreshments.exception.UserAlreadyExistsException;
import com.project.refreshments.factory.AuthenticatedUserFactory;
import com.project.refreshments.model.AuthenticatedUser;
import com.project.refreshments.repository.AccountRepository;
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

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AuthenticatedUserFactory authenticatedUserFactory;
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @Transactional
    public AuthenticatedUser register(RegistrationRequestDto registrationRequestDto) {
        log.debug("Registering user with information: {}", registrationRequestDto);
        if (userExists(registrationRequestDto.getCardId())) {
            throw new UserAlreadyExistsException(
                    "A user with the Card ID " + registrationRequestDto.getCardId() + " already exists.");
        }
        final UserEntity userEntity = userRepository
                .saveAndFlush(createUserEntity(LocalDateTime.now(), registrationRequestDto));
        final AccountEntity accountEntity = accountRepository.
                saveAndFlush(accountService.createAccountEntity(userEntity, LocalDateTime.now()));
        return authenticatedUserFactory.create(userEntity);
    }

    public AuthenticatedUser signIn(final AuthenticationRequestDto authenticationRequestDto) {
        try {
            final String username = authenticationRequestDto.getUsername();
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, authenticationRequestDto.getPin()));
            final UserEntity userEntity = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found."));
            return authenticatedUserFactory.create(userEntity);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Incorrect username or password.");
        }
    }

    private final boolean userExists(String cardId) {
        return userRepository.findByUsername(cardId).isPresent();
    }

    public UserEntity createUserEntity(final LocalDateTime createdDate,
                                       final RegistrationRequestDto registrationRequest) {
        return new UserEntity()
                .setEmployeeId(registrationRequest.getEmployeeId())
                .setUsername(registrationRequest.getCardId())
                .setCreationDate(createdDate)
                .setFirstName(registrationRequest.getFirstName())
                .setLastName(registrationRequest.getLastName())
                .setEmail(registrationRequest.getEmail())
                .setPassword(passwordEncoder.encode(registrationRequest.getPin()))
                .setCredentialsNonExpired(true);
    }

}
