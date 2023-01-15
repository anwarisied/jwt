package com.rm.roadmaps.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.rm.roadmaps.security.filter.AuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter=new AuthenticationFilter();
        authenticationFilter.setFilterProcessesUrl("/authenticate");
        http
                .csrf().disable()
                .authorizeHttpRequests(
                        (authz) -> authz
                                .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH)
                                .permitAll()
                                .anyRequest()
                                // Any other requests need be authenticated
                                .authenticated()
                                .and().addFilter(authenticationFilter))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        ;
        return http.build();
    }
}
