package com.zhongym.securitydemo.user;

import com.zhongym.securitydemo.oauth2.OAuth2Request;
import org.springframework.stereotype.Service;

@Service
public class FrontUserDetailsService {

    public FrontUser loadByOAuth2(OAuth2Request request) {
        if ("WX".equals(request.getGrantType())) {
            FrontUser user = new FrontUser("openId234232dsfs45e");
            return user;
        }
        return null;
    }
}
