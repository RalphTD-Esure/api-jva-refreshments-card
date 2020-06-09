package com.project.refreshments.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Accessors(chain = true)
@Table(name = "users")
@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserEntity implements UserDetails
{
    private static final long serialVersionUID = 1L;
    private static final List<GrantedAuthority> AUTHORITIES = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name ="password", nullable = false)
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AccountEntity account;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name ="credentials_non_expired", nullable = false)
    private Boolean credentialsNonExpired;


    public List<String> getRoles() {
        return AUTHORITIES.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority>
    getAuthorities() {
        return AUTHORITIES;
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof User) {
            return username.equals(((User) rhs).getUsername());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return "User{" + "id=" + employeeId + ", name=" + firstName + ", email=" + email + '}';
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
        return credentialsNonExpired;
    }

    @Override public boolean isEnabled()
    {
        return true;
    }
}