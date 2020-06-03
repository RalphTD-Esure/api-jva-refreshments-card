//package com.project.refreshments.service;
//
//import java.math.BigDecimal;
//import java.util.Optional;
//
//import com.project.api.refreshments.swagger.model.PurchaseRequest;
//import com.project.refreshments.entity.AccountEntity;
//import com.project.refreshments.repository.AccountRepository;
//import com.project.refreshments.repository.PurchaseRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PurchaseService
//{
//    PurchaseRepository purchaseRepository;
//    AccountRepository accountRepository;
//
//    public BigDecimal makePurchase(PurchaseRequest purchaseRequest)
//    {
//
//        Optional<AccountEntity> account = accountRepository.findById(purchaseRequest.getemployeeId());
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
