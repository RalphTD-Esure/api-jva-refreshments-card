package com.project.refreshments.service;

import com.project.refreshments.entity.AccountEntity;
import com.project.refreshments.entity.UserEntity;
import com.project.refreshments.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public AccountEntity createAccountEntity(final UserEntity userEntity, LocalDateTime creationDate) {
        return accountRepository.saveAndFlush(new AccountEntity().setUsername(userEntity.getUsername()).setUserId(userEntity.getId()).setActive(true).setBalance(
                BigDecimal.ZERO)).setCreationDate(creationDate);
    }
}
