package com.project.refreshments.api;

import com.project.refreshments.dto.TopUpRequestDto;
import com.project.refreshments.entity.AccountEntity;
import com.project.refreshments.entity.UserEntity;
import com.project.refreshments.repository.AccountRepository;
import com.project.refreshments.repository.UserRepository;
import com.project.refreshments.service.TopUpService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class TopUpServiceTests {

    private static final Integer ONBOARD_EMPLOYEE_ID = 123456;
    private static final String ONBOARD_CARD_ID = "r7jTG7dqBy5wGO4L";
    private static final String ONBOARD_FIRST_NAME = "John";
    private static final String ONBOARD_LAST_NAME = "Smith";
    private static final String ONBOARD_EMAIL = "john@smith.com";
    private static final String ONBOARD_PIN = "1234";


    @Mock
    AccountRepository accountRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    TopUpService topUpService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void topUpSuccess() {
        AccountEntity accountEntity = createAccountEntity();
        UserEntity userEntity = createUserEntity();
        TopUpRequestDto topUpRequestDto = createTopUpRequestDto();
        when(userRepository.findByUsername(topUpRequestDto.getUsername())).thenReturn(Optional.of(userEntity));
        when(accountRepository.findByUsername(accountEntity.getUsername())).thenReturn(Optional.of(accountEntity));
        BigDecimal newBalance = topUpService.addFunds(topUpRequestDto);
        assertThat(newBalance).isEqualTo(BigDecimal.valueOf(11));

    }

    private AccountEntity createAccountEntity() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1);
        accountEntity.setUserId(1);
        accountEntity.setUsername(ONBOARD_CARD_ID);
        accountEntity.setActive(true);
        accountEntity.setBalance(BigDecimal.TEN);
        accountEntity.setCreationDate(LocalDateTime.parse("2020-06-02T00:29:53.949"));
        return accountEntity;
    }

    private TopUpRequestDto createTopUpRequestDto() {
        TopUpRequestDto topUpRequestDto = new TopUpRequestDto();
        topUpRequestDto.setUsername("r7jTG7dqBy5wGO4L");
        topUpRequestDto.setAmount(BigDecimal.ONE);
        return topUpRequestDto;
    }

    private UserEntity createUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setUsername(ONBOARD_CARD_ID);
        userEntity.setPassword("1234");
        userEntity.setCredentialsNonExpired(true);
        userEntity.setEmployeeId(ONBOARD_EMPLOYEE_ID);
        userEntity.setCreationDate(LocalDateTime.parse("2020-06-02T00:28:53.949"));
        userEntity.setLastName(ONBOARD_LAST_NAME);
        userEntity.setFirstName(ONBOARD_FIRST_NAME);
        userEntity.setEmail(ONBOARD_EMAIL);
        return userEntity;
    }
}
