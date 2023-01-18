package com.rm.roadmaps.security;

import com.rm.roadmaps.security.filter.ExceptionHandlerFilter;
import com.rm.roadmaps.security.filter.JWTAuthenticationFilter;
import com.rm.roadmaps.security.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.rm.roadmaps.security.filter.AuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
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
                                .and().addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                                .addFilter(authenticationFilter)
                                .addFilterAfter(new JWTAuthenticationFilter(), AuthenticationFilter.class))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        ;
        return http.build();
    }
}
