package com.zhongym.securitydemo.user;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;


public class FrontUser extends User {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * OpenID
     */
    private String openId;

    /**
     * UnionID
     */
    private String unionId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 所属小程序类型   WECHAT-微信小程序  DOUYIN-抖音小程序
     */
    private String microAppType;

    public FrontUser(String userName) {
        super(userName, "password", AuthorityUtils.createAuthorityList());
    }
}
