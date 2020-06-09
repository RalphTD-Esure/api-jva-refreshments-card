package com.project.refreshments.repository;

import com.project.refreshments.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Integer> {

    Optional<StockEntity> findByItemId(Integer itemId);

}
