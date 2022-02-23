package com.osp.codechallenge.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * Class containing the configuration of spring security
 */
@Configuration
@Slf4j
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange()
                .pathMatchers("/shipments")
                .hasAuthority("ROLE_ADMIN")
                .anyExchange()
                .authenticated()
                .and()
                .formLogin().disable().httpBasic()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() -> {
                    swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    log.error(e.getMessage());
                })).accessDeniedHandler((swe, e) -> Mono.fromRunnable(() -> {
                    swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    log.error(e.getMessage());
                }))
                .and().build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("user1")
                .password(passwordEncoder().encode("user1Pass"))
                .roles("ADMIN")
                .build();

        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
