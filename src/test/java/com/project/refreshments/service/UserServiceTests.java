package com.project.refreshments.service;

import com.project.refreshments.dto.RegistrationRequestDto;
import com.project.refreshments.dto.UserCheckDto;
import com.project.refreshments.entity.UserEntity;
import com.project.refreshments.exception.UserAlreadyExistsException;
import com.project.refreshments.factory.AuthenticatedUserFactory;
import com.project.refreshments.model.AuthenticatedUser;
import com.project.refreshments.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTests {
    private static final Integer ONBOARD_EMPLOYEE_ID = 12345;
    private static final String ONBOARD_CARD_ID = "r7jTG7dqBy5wGO4L";
    private static final String ONBOARD_FIRST_NAME = "John";
    private static final String ONBOARD_LAST_NAME = "Smith";
    private static final String ONBOARD_EMAIL = "john@smith.com";
    private static final String ONBOARD_PIN = "1234";
    private static final LocalDateTime CREATED_DATE = LocalDateTime.now();

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountService accountService;

    @Mock
    private AuthenticatedUserFactory authenticatedUserFactory;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testUserCheckUserExists() {
        UserCheckDto userCheckDto = createUserCheckDto();
        UserEntity userEntity = createUserEntity();
        when(userRepository.findByUsername(userCheckDto.getCardId())).thenReturn(Optional.of(userEntity));
        String welcomeMessage = userService.welcome(userCheckDto.getCardId());
        assertThat(welcomeMessage).isEqualTo("Welcome " + userEntity.getFirstName() + ", please login with your Card ID and chosen PIN at /login.");
    }

    @Test
    public void testUserCheckNotUserExists() {
        UserCheckDto userCheckDto = createUserCheckDto();
        UserEntity userEntity = createUserEntity();
        when(userRepository.findByUsername(userCheckDto.getCardId())).thenReturn(Optional.empty());
        String welcomeMessage = userService.welcome(userCheckDto.getCardId());
        assertThat(welcomeMessage).isEqualTo("Welcome! Please register with Bows Formula 1 Food Hall at /registration to access features.");
    }

    @Test
    public void testRegistrationSuccess() {
        RegistrationRequestDto registrationRequest = createRegistrationRequest();

        ArgumentCaptor<UserEntity> userEntityArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);
        when(userRepository.findByUsername(ONBOARD_CARD_ID)).thenReturn(Optional.empty());
        when(authenticatedUserFactory.create(userEntityArgumentCaptor.capture()))
                .thenReturn(new AuthenticatedUser().setUsername(ONBOARD_CARD_ID));
        when(userService.createUserEntity(CREATED_DATE, registrationRequest)).thenReturn(any(UserEntity.class));
        AuthenticatedUser authenticatedUser = userService.register(registrationRequest);
        assertThat(authenticatedUser.getUsername()).isEqualTo(ONBOARD_CARD_ID);
        verify(userRepository).saveAndFlush(userEntityArgumentCaptor.capture());
        assertThat(authenticatedUser.getUsername()).isEqualTo(ONBOARD_CARD_ID);
        assertThat(userEntityArgumentCaptor.getValue())
                .hasFieldOrPropertyWithValue("username", ONBOARD_CARD_ID)
                .hasFieldOrPropertyWithValue("employeeId", ONBOARD_EMPLOYEE_ID)
                .hasFieldOrPropertyWithValue("firstName", ONBOARD_FIRST_NAME)
                .hasFieldOrPropertyWithValue("lastName", ONBOARD_LAST_NAME)
                .hasFieldOrPropertyWithValue("email", ONBOARD_EMAIL);
        assertThat(userEntityArgumentCaptor.getValue())
                .hasFieldOrProperty("creationDate")
                .hasFieldOrProperty("password");
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testRegistrationThrowsUserAlreadyExistsException() {
        RegistrationRequestDto registrationRequestDto = createRegistrationRequest();
        UserEntity userEntity = createUserEntity();
        when(userRepository.findByUsername(ONBOARD_CARD_ID)).thenReturn(Optional.of(userEntity));

        userService.register(registrationRequestDto);
    }

    private RegistrationRequestDto createRegistrationRequest() {
        RegistrationRequestDto registrationRequest = new RegistrationRequestDto();
        registrationRequest.setEmployeeId(ONBOARD_EMPLOYEE_ID);
        registrationRequest.setCardId(ONBOARD_CARD_ID);
        registrationRequest.setFirstName(ONBOARD_FIRST_NAME);
        registrationRequest.setLastName(ONBOARD_LAST_NAME);
        registrationRequest.setEmail(ONBOARD_EMAIL);
        registrationRequest.setPin(ONBOARD_PIN);
        registrationRequest.setConfirmPin(ONBOARD_PIN);

        return registrationRequest;
    }

    private UserEntity createUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(ONBOARD_CARD_ID);
        userEntity.setPassword(passwordEncoder.encode(ONBOARD_PIN));
        userEntity.setCredentialsNonExpired(true);
        userEntity.setEmployeeId(ONBOARD_EMPLOYEE_ID);
        userEntity.setCreationDate(LocalDateTime.now());
        userEntity.setLastName(ONBOARD_LAST_NAME);
        userEntity.setFirstName(ONBOARD_FIRST_NAME);
        userEntity.setEmail(ONBOARD_EMAIL);

        return userEntity;
    }


    private UserCheckDto createUserCheckDto() {
        UserCheckDto userCheckDto = new UserCheckDto();
        userCheckDto.setCardId(ONBOARD_CARD_ID);
        return userCheckDto;
    }

}
