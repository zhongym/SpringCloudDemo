package com.zhongym.securitydemo.oauth2;

import com.zhongym.securitydemo.user.FrontUser;
import com.zhongym.securitydemo.user.FrontUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationProvider implements AuthenticationProvider {
    private final FrontUserDetailsService frontUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OAuth2AuthenticationToken codeAuthentication = (OAuth2AuthenticationToken) authentication;
        //根据code获取微信用户
        FrontUser user = frontUserDetailsService.loadByOAuth2(codeAuthentication.getRequest());
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return new OAuth2AuthenticationToken(user, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OAuth2AuthenticationToken.class.isAssignableFrom(authentication);
    }
}
