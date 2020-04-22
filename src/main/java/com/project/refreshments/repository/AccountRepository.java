package com.project.refreshments.repository;

import com.project.refreshments.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long>
{
}
