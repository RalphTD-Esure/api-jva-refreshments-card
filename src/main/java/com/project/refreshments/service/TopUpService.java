package com.project.refreshments.service;

import com.project.refreshments.dto.TopUpRequestDto;
import com.project.refreshments.entity.AccountEntity;
import com.project.refreshments.entity.TopUpEntity;
import com.project.refreshments.repository.AccountRepository;
import com.project.refreshments.repository.TopUpRepository;
import com.project.refreshments.security.UserDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final UserDetailsServiceImp userDetailsServiceImp;

    public BigDecimal addFunds(TopUpRequestDto topUpRequestDto) {

        String username = topUpRequestDto.getUsername();
        BigDecimal topUpAmount = topUpRequestDto.getAmount();

        UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(username);
        Optional<AccountEntity> accountEntity = accountRepository.findByUsername(userDetails.getUsername());

        if (accountEntity != null) {
            AccountEntity account = accountEntity.get();
            TopUpEntity topUpEntity = createTopUpEntity(topUpRequestDto, account);
            topUpRepository.save(topUpEntity);
            account.addToBalance(topUpAmount);
            accountRepository.save(account);
            return account.getBalance();
        } else {
            throw new IllegalArgumentException("No account found for Id");
        }
    }

    private TopUpEntity createTopUpEntity(TopUpRequestDto topUpRequestDto, AccountEntity account) {
        return new TopUpEntity().setTopUpDate(LocalDateTime.now()).setAmount(topUpRequestDto.getAmount()).setAccountId(account.getId());
    }
}
