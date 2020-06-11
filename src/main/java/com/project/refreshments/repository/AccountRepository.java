package com.project.refreshments.repository;

import com.project.refreshments.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    Optional<AccountEntity> findById(Integer accountId);

    Optional<AccountEntity> findByUsername(String username);
}
