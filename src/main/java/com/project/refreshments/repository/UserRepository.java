package com.project.refreshments.repository;

import java.util.Optional;

import com.project.refreshments.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>
{
   Optional<UserEntity> findByEmployeeId(Integer employeeId);

   Optional<UserEntity> findByUsername(String username);
}
