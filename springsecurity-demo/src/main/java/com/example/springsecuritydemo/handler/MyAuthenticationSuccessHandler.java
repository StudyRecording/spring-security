package com.example.springsecuritydemo.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private String url;

    public MyAuthenticationSuccessHandler(String url) {
        this.url = url;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("name: " + authentication.getName());
        System.out.println("principal: " + authentication.getPrincipal());
        System.out.println("details: " + authentication.getDetails());
        System.out.println("authorities: " + authentication.getAuthorities());
        response.sendRedirect(url);
    }
}
