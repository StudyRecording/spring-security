package com.example.springsecuritydemo.config;

import com.example.springsecuritydemo.handler.MyAccessDeniedHandler;
import com.example.springsecuritydemo.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .usernameParameter("username123")
                .passwordParameter("password123")
//                .loginPage("/login.html")
                .loginPage("/toLogin")
                .loginProcessingUrl("/login")
                .successForwardUrl("/toMain")
//                .successHandler(new MyAuthenticationSuccessHandler("/main.html"))
                .failureForwardUrl("/toError");
//                .failureHandler(new MyAuthenticationFailureHandler("/error.html"));

        http.authorizeRequests()
                .antMatchers("/error.html").permitAll()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/css/**", "/js/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/toLogin").permitAll()
//                .antMatchers("/main1.html").hasAuthority("admiN")
//                .antMatchers("/main1.html").hasAnyAuthority("admin", "admiN")
//                .antMatchers("/main1.html").hasRole("abC")
//                .antMatchers("/main.html").access("hasRole('abc')")
//                .antMatchers("/main1.html").hasAnyRole("abc", "abC")
//                .antMatchers("/main1.html").hasIpAddress("127.0.0.1")
//                .antMatchers("/**/*.png").permitAll()
//                .anyRequest().access("@myServiceImpl.hasPermission(request, authentication)");
                .anyRequest().authenticated();

        http.rememberMe()
                .userDetailsService(userDetailsService)
                .tokenRepository(persistentTokenRepository)
                .tokenValiditySeconds(60);

        http.exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler);

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html");

//        http.csrf().disable();
    }

    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepository() {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
//        repository.setCreateTableOnStartup(true);
        return repository;
    }
}
