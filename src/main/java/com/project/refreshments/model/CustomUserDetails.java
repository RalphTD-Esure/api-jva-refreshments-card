package com.project.refreshments.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.project.refreshments.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails
{
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Integer employeeId;
    private String pin;
    private LocalDateTime creationDate;
    private boolean active;
    private List<SimpleGrantedAuthority> authorities;

    public CustomUserDetails(UserEntity userEntity) {
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
        this.email = userEntity.getEmail();
        this.employeeId = userEntity.getEmployeeId();
        this.pin = userEntity.getPin();
        this.creationDate = userEntity.getCreationDate();
        this.active = userEntity.getActive();
        this.authorities = Arrays.stream(userEntity.getRoles().split(","))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public CustomUserDetails() {

    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities;
    }

    @Override public String getPassword()
    {
        return password;
    }

    @Override public String getUsername()
    {
        return username;
    }

    @Override public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override public boolean isEnabled()
    {
        return active;
    }
}

