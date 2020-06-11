package com.project.refreshments.repository;

import com.project.refreshments.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Integer> {

    Optional<PurchaseEntity> findByAccountId(Integer accountId);

}
