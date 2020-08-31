package com.zhongym.securitydemo.oauth2;

import lombok.Data;

@Data
public class OAuth2Request {
    /**
     * 应用类型
     */
    private String grantType;

    private String code;
}
