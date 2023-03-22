package com.cpan252.tekkenreborn.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cpan252.tekkenreborn.repository.UserRepository;
import com.cpan252.tekkenreborn.model.User;

import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository repository) {
        return username -> {
            User user = repository.findByUsername(username);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .requestMatchers(toH2Console()).permitAll()
                .requestMatchers("/design", "/fighterlist")
                .hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/design", true)
                .and()
                .logout()
                .logoutSuccessUrl("/")

                // Make H2-Console non-secured; for debug purposes
                .and()
                .csrf()
                .ignoringRequestMatchers(toH2Console())
                .ignoringRequestMatchers("/fighterlist")
                .ignoringRequestMatchers("/design")
                // Allow pages to be loaded in frames from the same origin; needed for
                // H2-Console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .build();
    }
}
