package com.example.shopbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
