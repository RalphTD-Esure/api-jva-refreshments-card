//package com.project.refreshments.service;
//
//import com.project.refreshments.dto.PurchaseRequestDto;
//import com.project.refreshments.entity.AccountEntity;
//import com.project.refreshments.repository.AccountRepository;
//import com.project.refreshments.repository.PurchaseRepository;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.Optional;
//
//@Service
//public class PurchaseService
//{
//    PurchaseRepository purchaseRepository;
//    AccountRepository accountRepository;
//
//    public BigDecimal makePurchase(PurchaseRequestDto purchaseRequestDto)
//    {
//
//        Optional<AccountEntity> account = accountRepository.findByUsername(purchaseRequestDto.getUsername());
//
//        if (account.isPresent())
//        {
//            AccountEntity accountEntity = account.get();
//            purchaseRepository.save(purchaseEntity);
//            accountEntity.payFromBalance(transactionEntity.getAmount());
//            accountRepository.save(accountEntity);
//
//            return accountEntity.getBalance();
//        }
//        else
//        {
//            throw new IllegalArgumentException("No account found for Id");
//        }
//    }
//}
