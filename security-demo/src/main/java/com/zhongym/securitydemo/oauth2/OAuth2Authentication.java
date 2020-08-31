package com.zhongym.securitydemo.oauth2;

import com.zhongym.securitydemo.user.FrontUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class OAuth2Authentication extends AbstractAuthenticationToken {

    private FrontUser principal;
    private OAuth2Request request;

    public OAuth2Authentication(OAuth2Request request) {
        super(null);
        this.request = request;
    }

    public OAuth2Authentication(FrontUser principal, Collection<? extends GrantedAuthority> authorities) {
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
