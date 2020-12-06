package com.springsecurity.oauth2.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class User implements UserDetails {

    private List<GrantedAuthority> grantedAuthority;

    private String username;

    private String password;

    public User() {
    }

    public User(List<GrantedAuthority> grantedAuthority, String username, String password) {
        this.grantedAuthority = grantedAuthority;
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return grantedAuthority;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
