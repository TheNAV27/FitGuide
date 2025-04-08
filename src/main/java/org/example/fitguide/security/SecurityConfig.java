package org.example.fitguide.security;

import org.example.fitguide.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(httpForm -> {
                    httpForm.loginPage("/api/").permitAll();
                        })
                .authorizeHttpRequests(registry ->{
                    registry.requestMatchers("/Register").permitAll();
                    registry.anyRequest().authenticated();
                        })
                .build();


    }
}
