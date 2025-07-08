package com.vab.CafeSupreme.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class WebSecurityConfig {

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(getUserDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
//                        auth -> auth.requestMatchers("/**").permitAll()
                        auth -> auth.requestMatchers("/images/**").permitAll().
                                requestMatchers("/user/register", "/user/login", "/", "/home").permitAll()
                                .anyRequest().authenticated())
                .formLogin(login -> login.loginPage("/user/login").usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/home", true).permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/home").permitAll());

        return http.build();
    }
}
