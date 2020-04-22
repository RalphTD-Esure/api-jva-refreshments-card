package com.project.refreshments.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.project.refreshments.entity.AccountEntity;
import com.project.refreshments.entity.TransactionEntity;
import com.project.refreshments.repository.AccountRepository;
import com.project.refreshments.repository.TransactionRepository;

public class AddFundsService
{
    TransactionRepository transactionRepository;
    AccountRepository accountRepository;

    public BigDecimal makePurchase(TransactionEntity transactionEntity)
    {

        Optional<AccountEntity> account = accountRepository.findById(transactionEntity.getAccountId());

        if (account.isPresent())
        {
            AccountEntity accountEntity = account.get();
            transactionRepository.save(transactionEntity);
            accountEntity.addToBalance(transactionEntity.getPrice());
            accountRepository.save(accountEntity);

            return accountEntity.getBalance();
        }
        else
        {
            throw new IllegalArgumentException("No account found for Id");
        }
    }
}
