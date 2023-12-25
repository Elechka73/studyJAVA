package com.example.lr15.configuration;

import com.example.lr15.entities.User;
import com.example.lr15.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/dishs/addOrUpdate/**").hasRole("ADMIN")
                        .requestMatchers("/dishs/show/**").authenticated()
                        .requestMatchers("/dishs/filter/**").authenticated()
                        .anyRequest().permitAll())
                .formLogin((form) -> form
                        .loginPage("/")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll())
                .logout((logout) -> logout
                        .logoutSuccessUrl("/").permitAll()).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return username -> {
            User user = userService.getUserByUserName(username);
            //userService.encode(username);
            return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password(user.getPassword()).authorities(userService.getAuthorityByusername(username)).build();
        };
    }
}
