package com.project.refreshments.repository;

import com.project.refreshments.entity.TopUpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<TopUpEntity, Long>
{

}
