package com.project.refreshments.model;

import java.util.Collection;
import java.util.stream.Collectors;

import com.project.refreshments.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails
{
    private UserEntity userEntity;

    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userEntity.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getName().toString())).collect(
                Collectors.toList());
    }
    public int getId() {
        return userEntity.getUserId();
    }
    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }
    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    public UserEntity getUserDetails() {
        return userEntity;
    }
}

