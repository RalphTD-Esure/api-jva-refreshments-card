package com.project.refreshments.service;

import com.project.refreshments.dto.PurchaseRequestDto;
import com.project.refreshments.entity.AccountEntity;
import com.project.refreshments.entity.PurchaseEntity;
import com.project.refreshments.entity.StockEntity;
import com.project.refreshments.exception.InsufficientFundsException;
import com.project.refreshments.model.Item;
import com.project.refreshments.repository.AccountRepository;
import com.project.refreshments.repository.PurchaseRepository;
import com.project.refreshments.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final AccountRepository accountRepository;
    private final StockRepository stockRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, AccountRepository accountRepository,
                           StockRepository stockRepository) {
        this.purchaseRepository = purchaseRepository;
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
    }

    public BigDecimal makePurchase(PurchaseRequestDto purchaseRequestDto) {

        BigDecimal totalPrice = calculateTotalPrice(purchaseRequestDto.getItemCodes());
        Optional<AccountEntity> accountOptional = accountRepository.findByUsername(purchaseRequestDto.getUsername());
        AccountEntity account = accountOptional.get();
        List<PurchaseEntity> purchaseEntities = createPurchaseEntities(purchaseRequestDto, accountOptional.get());
        boolean sufficientFunds = account.getBalance().compareTo(totalPrice) == 1;
        if (!accountOptional.isPresent()) {
            throw new IllegalArgumentException("No account found for ID");
        } else if (!sufficientFunds) {
            throw new InsufficientFundsException("Insufficient funds to complete purchase.");
        } else {
            for (PurchaseEntity purchaseEntity : purchaseEntities) {

                purchaseRepository.saveAndFlush(purchaseEntity);
            }
            account.subtractFromBalance(totalPrice);
            accountRepository.saveAndFlush(account);
            return account.getBalance();
        }
    }

    private List<PurchaseEntity> createPurchaseEntities(PurchaseRequestDto purchaseRequestDto, AccountEntity
            account) {
        List<Item> items = purchaseRequestDto.getItemCodes();
        List<PurchaseEntity> purchaseEntities = new ArrayList<>();
        for (Item item : items) {
            purchaseEntities.add(new PurchaseEntity().setItemId(item.getItemCode()).setAccountId(account.getId())
                    .setPrice(stockRepository.findByItemId(item.getItemCode()).get().getItemPrice()).setPurchaseDate(
                            LocalDateTime.now()));
            StockEntity stockEntity = stockRepository.findByItemId(item.getItemCode()).get();
            stockEntity.subtractFromQuantity(1);
            stockRepository.saveAndFlush(stockEntity);
        }
        return purchaseEntities;
    }

    private BigDecimal calculateTotalPrice(List<Item> items) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Item item : items) {
            StockEntity stockEntity = stockRepository.findByItemId(item.getItemCode()).get();
            BigDecimal itemPrice = stockEntity.getItemPrice();
            totalPrice = totalPrice.add(itemPrice);
        }
        return totalPrice;
    }
}
