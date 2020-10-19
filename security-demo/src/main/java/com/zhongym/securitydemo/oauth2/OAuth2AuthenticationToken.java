package com.zhongym.securitydemo.oauth2;

import com.zhongym.securitydemo.user.FrontUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class OAuth2AuthenticationToken extends AbstractAuthenticationToken {

    private FrontUser principal;
    private OAuth2Request request;

    public OAuth2AuthenticationToken(OAuth2Request request) {
        super(null);
        this.request = request;
    }

    public OAuth2AuthenticationToken(FrontUser principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public OAuth2Request getRequest() {
        return request;
    }
}
