package com.example.springsecuritydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        if (!s.equals("admin")) {
            throw new UsernameNotFoundException("查询不到用户名");
        }

        String password = passwordEncoder.encode("123");

        // 权限和角色, 其中角色必须以ROLE_开头
        return new User(s, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin, normal, ROLE_abc, /main.html"));
    }
}
