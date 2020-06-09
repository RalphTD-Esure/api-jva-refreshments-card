package com.project.refreshments.repository;

import com.project.refreshments.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>
{
   Optional<UserEntity> findByEmployeeId(Integer employeeId);

   Optional<UserEntity> findByUsername(String username);
}
