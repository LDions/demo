package com.hzy.mydemo.modules.login.security;

import com.hzy.mydemo.modules.basic.domain.Users;
import com.hzy.mydemo.modules.basic.repository.AccountsRepository;
import com.hzy.mydemo.modules.basic.repository.UserRoleRepository;
import com.hzy.mydemo.modules.basic.repository.UsersRepository;
import com.hzy.mydemo.modules.common.exception.BadRequestAlertException;
import com.hzy.mydemo.modules.firstversion.repository.UserRepository;
import java.util.*;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
@Slf4j
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UsersRepository usersRepository;
    private final AccountsRepository accountsRepository;
    private final UserRoleRepository userRoleRepository;

    public DomainUserDetailsService(
        UserRepository userRepository,
        UsersRepository usersRepository,
        AccountsRepository accountsRepository,
        UserRoleRepository userRoleRepository
    ) {
        this.userRepository = userRepository;
        this.usersRepository = usersRepository;
        this.accountsRepository = accountsRepository;
        this.userRoleRepository = userRoleRepository;
    }

    /*
     *在此处实现自定义登录逻辑  通过查询数据库（或者是缓存、或者是其他的存储形式）来获取用户信息，然后组装成一个UserDetails
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);
        //        UserModel userModel = null;
        return accountsRepository
            .findByOpenIdAndCategoriesAndDeletedIsFalse(login, AuthoritiesConstants.CATEGORY_LOGIN)
            .map(
                accounts -> {
                    return usersRepository
                        .findByIdAndState(accounts.getUserId(), AuthoritiesConstants.STATE_NORMAL)
                        .map(
                            users -> {
                                return createSpringSecurityUser(login, users);
                            }
                        )
                        .get();
                }
            )
            .orElseThrow(() -> new BadRequestAlertException("账号或密码输入错误"));
        //        return userModel;
        //        //用户信息校验（邮箱校验）
        //        if (new EmailValidator().isValid(login, null)) {
        //            return userRepository
        //                .findOneWithAuthoritiesByEmailIgnoreCase(login)
        //                .map(user -> createSpringSecurityUser(login, user))
        //                .orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
        //        }
        //
        //        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        //        return userRepository
        //            .findOneWithAuthoritiesByLogin(lowercaseLogin)
        //            .map(user -> createSpringSecurityUser(lowercaseLogin, user))
        //            .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
    }

    private UserModel createSpringSecurityUser(String loginCode, Users users) {
        List<GrantedAuthority> grantedAuthorities = userRoleRepository
            .getAllRoleCodeByUserId(users.getId())
            .stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
        return new UserModel(loginCode, users.getPassword(), grantedAuthorities, users.getId(), users.getName(), users.getPhone());
    }
    //    private UserModel createSpringSecurityUser(String lowercaseLogin, User user) {
    //        if (!user.isActivated()) {
    //            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
    //        }
    //        List<GrantedAuthority> grantedAuthorities = user
    //            .getAuthorities()
    //            .stream()
    //            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
    //            .collect(Collectors.toList());
    //        return new UserModel(user.getLogin(), user.getPassword(), grantedAuthorities);
    //    }
}
