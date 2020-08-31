package com.zhongym.securitydemo.config;

import com.zhongym.securitydemo.jwt.JwtSecurityConfigurer;
import com.zhongym.securitydemo.oauth2.OAuth2SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

//@EnableAuthorizationServer
@Primary
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    /**
     * 密码加密，匹配器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 用户加载服务
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                String password = passwordEncoder().encode("zs");
                return User.withUsername("zs").password(password).authorities(new ArrayList<>()).build();
            }
        };
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/auth/oauth2");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //无条件允许访问
                .antMatchers(
                        "/auth/oauth2",
                        "/hello",
                        "/error"
                ).permitAll()
                ////需认证通过
                .anyRequest().authenticated()
                .and()
                //无状态
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf()
                .disable()
                .apply(oAuth2SecurityConfiguration);
    }


//    @Bean
//    public JwtSecurityConfigurer jwtSecurityConfigurer() {
//        return new JwtSecurityConfigurer();
//    }

    @Autowired
    private OAuth2SecurityConfiguration oAuth2SecurityConfiguration;
}