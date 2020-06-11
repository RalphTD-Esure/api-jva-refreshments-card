package com.project.refreshments.repository;

import java.util.Optional;

import com.project.refreshments.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Integer>
{
    Optional<PurchaseEntity> findByAccountId(Integer accountId);

}
