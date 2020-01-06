package com.project.refreshments.repository;

import com.project.refreshments.entity.UsersCreation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UsersCreation, Long>
{
}
