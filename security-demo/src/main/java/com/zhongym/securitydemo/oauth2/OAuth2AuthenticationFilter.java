package com.zhongym.securitydemo.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OAuth2AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public OAuth2AuthenticationFilter(ObjectMapper objectMapper) {
        super(new AntPathRequestMatcher("/auth/oauth2", "POST"));
        this.objectMapper = objectMapper;
    }

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) {
        if (!request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        //取参数
        OAuth2Request oAuth2Request = objectMapper.readValue(request.getInputStream(), OAuth2Request.class);

        OAuth2AuthenticationToken oAuth2Authentication = new OAuth2AuthenticationToken(oAuth2Request);
        setDetails(request, oAuth2Authentication);

        return this.getAuthenticationManager().authenticate(oAuth2Authentication);
    }

    protected void setDetails(HttpServletRequest request,
                              OAuth2AuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}