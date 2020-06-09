package com.project.refreshments.repository;

import com.project.refreshments.entity.TopUpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopUpRepository extends JpaRepository<TopUpEntity, Integer>
{

}
