package com.project.refreshments.security;

import java.util.Optional;

import com.project.refreshments.entity.UserEntity;
import com.project.refreshments.model.CustomUserDetails;
import com.project.refreshments.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImp implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        log.info("Loading user with username: " + username);
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        userEntity.orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
        return userEntity.map(CustomUserDetails::new).get();
    }
}