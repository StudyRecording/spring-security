package com.example.springsecuritydemo.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Service
public class MyServiceImpl implements MyService {
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        String requestURI = request.getRequestURI();

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetail = (UserDetails) principal;
            Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
            return authorities.contains(new SimpleGrantedAuthority(requestURI));
        }
        return false;
    }
}
