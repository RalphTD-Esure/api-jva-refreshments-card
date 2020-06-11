package com.project.refreshments.service;

import com.project.refreshments.dto.TopUpRequestDto;
import com.project.refreshments.entity.AccountEntity;
import com.project.refreshments.entity.TopUpEntity;
import com.project.refreshments.entity.UserEntity;
import com.project.refreshments.repository.AccountRepository;
import com.project.refreshments.repository.TopUpRepository;
import com.project.refreshments.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TopUpService {

    private final TopUpRepository topUpRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public BigDecimal addFunds(final TopUpRequestDto topUpRequestDto) {

        String username = topUpRequestDto.getUsername();
        BigDecimal topUpAmount = topUpRequestDto.getAmount();

        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
        UserEntity userEntity = userEntityOptional.get();
        Optional<AccountEntity> accountEntityOptional = accountRepository.findByUsername(userEntity.getUsername());

        if (accountEntityOptional.isPresent()) {
            AccountEntity account = accountEntityOptional.get();
            TopUpEntity topUpEntity = createTopUpEntity(topUpRequestDto, account);
            account.addToBalance(topUpAmount);
            topUpRepository.saveAndFlush(topUpEntity);
            accountRepository.saveAndFlush(account);
            return account.getBalance();
        } else {
            throw new IllegalArgumentException("No account found for ID");
        }
    }

    public TopUpEntity createTopUpEntity(TopUpRequestDto topUpRequestDto, AccountEntity account) {
        return new TopUpEntity().setAccountId(account.getId()).setAmount(topUpRequestDto.getAmount()).setTopUpDate(LocalDateTime.now());
    }
}
