package com.hzy.mydemo.modules.login.security.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;

    public JWTConfigurer(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    /*
    我们实现了自定义的token生成类，以及通过一个filter来拦截客户端请求，解析其中的token，复原无状态下的"session"，让当前请求处理线程中具有认证授权数据，
    后面的业务逻辑才能执行。下面，我们需要将自定义的内容整合到spring security中。
     */
    @Override
    public void configure(HttpSecurity http) {
        //当返回了这个token那如何实现下次请求其他APi的时候按照JWT的标准带上这个token进行验证呢？
        // 前面说过Security是通过一层层的filter处理web请求的，我们也需要加一个JWTfilter，并把它加入过滤器链中
        JWTFilter customFilter = new JWTFilter(tokenProvider);
        //这里就是把JWtfilter加进去
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
