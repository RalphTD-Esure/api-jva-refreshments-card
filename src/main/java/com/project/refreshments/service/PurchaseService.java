package com.project.refreshments.service;

import com.project.refreshments.dto.PurchaseRequestDto;
import com.project.refreshments.entity.AccountEntity;
import com.project.refreshments.entity.StockEntity;
import com.project.refreshments.model.Item;
import com.project.refreshments.repository.AccountRepository;
import com.project.refreshments.repository.PurchaseRepository;
import com.project.refreshments.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService
{
    private final PurchaseRepository purchaseRepository;
    private final AccountRepository accountRepository;
    private final StockRepository stockRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, AccountRepository accountRepository, StockRepository stockRepository) {
        this.purchaseRepository = purchaseRepository;
        this.accountRepository = accountRepository;
        this.stockRepository = stockRepository;
    }


    public BigDecimal makePurchase(PurchaseRequestDto purchaseRequestDto)
    {
        BigDecimal totalPrice = calculateTotalPrice(purchaseRequestDto.getItemCodes());

        Optional<AccountEntity> account = accountRepository.findByUsername(purchaseRequestDto.getUsername());

        if (account.isPresent())
        {
            AccountEntity accountEntity = account.get();
            purchaseRepository.save(purchaseEntity);
            accountEntity.payFromBalance(transactionEntity.getAmount());
            accountRepository.save(accountEntity);

            return accountEntity.getBalance();
        }
        else
        {
            throw new IllegalArgumentException("No account found for Id");
        }
    }

    private BigDecimal calculateTotalPrice(List<Item> items)
    {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Item item : items)
        {
            StockEntity stockEntity = stockRepository.findByItemId(item.getItemCode()).get();
            BigDecimal itemPrice = stockEntity.getItemPrice();
            totalPrice.add(itemPrice);
        }
        return totalPrice;
    }
}
