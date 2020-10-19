package com.zhongym.securitydemo.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhongym.securitydemo.jwt.JwtAuthenticationFailureHandler;
import com.zhongym.securitydemo.jwt.JwtAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class OAuth2SecurityConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private OAuth2AuthenticationProvider auth2AuthenticationProvider;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;
    @Autowired
    private JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        OAuth2AuthenticationFilter filter = new OAuth2AuthenticationFilter(objectMapper);
        filter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        filter.setAuthenticationSuccessHandler(jwtAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(jwtAuthenticationFailureHandler);

        builder.authenticationProvider(auth2AuthenticationProvider)
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
