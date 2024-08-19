package com.pedido.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(csrf -> csrf.disable())
    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .authorizeHttpRequests(req -> {
        
        RequestMatcher matcher = new AntPathRequestMatcher("/pedidos", HttpMethod.GET.name());
        RequestMatcher matcher2 = new AntPathRequestMatcher("/pedidos", HttpMethod.POST.name());
        RequestMatcher matcher3 = new AntPathRequestMatcher("/pedidos/{idPedido}/confirmado", HttpMethod.PATCH.name());
        RequestMatcher matcher4 = new AntPathRequestMatcher("/pedidos/{idPedido}", HttpMethod.DELETE.name());

        req.requestMatchers(matcher).hasRole("LOJA");
        req.requestMatchers(matcher).hasRole("CLIENTE");
        req.requestMatchers(matcher2).hasRole("CLIENTE");
        req.requestMatchers(matcher3).hasRole("CLIENTE");
        req.requestMatchers(matcher4).hasRole("CLIENTE");


        req.anyRequest().authenticated();
    })
    .addFilterBefore(securityFilter, SecurityContextPersistenceFilter.class)
    .build();
    }
}