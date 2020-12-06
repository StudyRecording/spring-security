package com.example.springsecuritydemo.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface MyService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
