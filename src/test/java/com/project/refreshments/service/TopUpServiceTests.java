package com.project.refreshments.api;

import com.project.refreshments.entity.AccountEntity;
import com.project.refreshments.entity.TopUpEntity;
import com.project.refreshments.repository.AccountRepository;
import com.project.refreshments.repository.TopUpRepository;
import com.project.refreshments.service.TopUpService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class TopUpServiceTests {

    @Mock
    TopUpRepository topUpRepository;

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    TopUpService topUpService;

    @Test
    public void topUpSuccess() {
        AccountEntity accountEntity = createAccountEntity();
        TopUpEntity topUpEntity = createTopUpEntity();

        when(accountRepository.findByUsername(accountEntity.getUsername())).thenReturn(Optional.of(accountEntity));
        BigDecimal newBalance = topUpService.addFunds()

    }

    private AccountEntity createAccountEntity() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUsername("r7jTG7dqBy5wGO4L");
        accountEntity.setActive(true);
        accountEntity.setBalance(BigDecimal.TEN);
        accountEntity.setCreationDate(LocalDateTime.now());
        return accountEntity;
    }

    private TopUpEntity createTopUpEntity() {
        TopUpEntity topUpEntity = new TopUpEntity();
        topUpEntity.setTopUpId(1);
        topUpEntity.setAccountId(1234);
        topUpEntity.setTopUpDate(LocalDateTime.now());
        topUpEntity.setAmount(BigDecimal.ONE);
    }
}
