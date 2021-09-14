package com.hzy.mydemo.modules.login.security;

import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @description:
 * @author: ruowei
 * @time: 2021/9/13 15:35
 */
public class UserModel extends User {

    private Long id;

    private String loginCode;

    private String name;

    private String phone;

    public UserModel(
        String username,
        String password,
        Collection<? extends GrantedAuthority> authorities,
        Long id,
        String name,
        String phone
    ) {
        super(username, password, authorities);
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public UserModel(
        String username,
        String password,
        Collection<? extends GrantedAuthority> authorities,
        Long id,
        String name,
        String phone,
        String loginCode
    ) {
        super(username, password, authorities);
        this.id = id;
        this.name = name;
        this.loginCode = loginCode;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
