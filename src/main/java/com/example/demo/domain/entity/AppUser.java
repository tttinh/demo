package com.example.demo.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "application_user")
@Getter
@Setter
@NoArgsConstructor
public class AppUser implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;
    private String password;

    @Column(columnDefinition="TIMESTAMP NOT NULL DEFAULT NOW()", updatable = false, insertable = false)
    private Timestamp createdAt;

    @Column(columnDefinition="TIMESTAMP NOT NULL DEFAULT NOW() ON UPDATE NOW()", updatable = false, insertable = false)
    private Timestamp updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return email;
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
}
