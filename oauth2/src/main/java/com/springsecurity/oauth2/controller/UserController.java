package com.springsecurity.oauth2.controller;

import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACVerifier;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/principal")
    public Object getPrincipal(Authentication authentication, HttpServletRequest request) {

        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(authorization.lastIndexOf("bearer") + 7);
//        return authentication.getPrincipal();
//        return null;
        HMACVerifier verifier = HMACVerifier.newVerifier("jwt_token");
        JWT decode = JWT.getDecoder().decode(token, verifier);
        return decode.getAllClaims();
    }
}
