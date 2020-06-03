package com.project.refreshments.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.project.api.refreshments.swagger.model.TopUpRequest;
import com.project.refreshments.entity.AccountEntity;
import com.project.refreshments.repository.AccountRepository;
import com.project.refreshments.repository.TopUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TopUpService
{
    TopUpRepository topUpRepository;
    AccountRepository accountRepository;

    public BigDecimal addFunds(TopUpRequest topUpRequest)
    {
        Integer employeeId = topUpRequest.getEmployeeId();
        BigDecimal topUpAmount = topUpRequest.getTopUpAmount();

        Optional<AccountEntity> account = accountRepository.findById(employeeId);

        if (account.isPresent())
        {
            AccountEntity accountEntity = account.get();
            accountEntity.addToBalance(topUpAmount);
            accountRepository.save(accountEntity);
            return accountEntity.getBalance();
        }
        else
        {
            throw new IllegalArgumentException("No account found for Id");
        }
    }
}
