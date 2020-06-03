package com.project.refreshments.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Entity
@Accessors(chain = true)
@Table(name = "users")
@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    private static final List<GrantedAuthority> AUTHORITIES = Collections.singletonList(new SimpleGrantedAuthority("USER"));

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name ="pin", nullable = false)
    private String pin;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name ="credentials_non_expired", nullable = false)
    private Boolean credentialsNonExpired;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id") })

    private Set<AuthorityEntity> authorities = new HashSet<>();

    public List<String> getRoles() {
        return AUTHORITIES.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
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

}