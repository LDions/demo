package com.hzy.mydemo.modules.login.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hzy.mydemo.modules.basic.domain.Users;
import com.hzy.mydemo.modules.basic.repository.AccountsRepository;
import com.hzy.mydemo.modules.basic.repository.UsersRepository;
import com.hzy.mydemo.modules.common.exception.BadRequestAlertException;
import com.hzy.mydemo.modules.common.exception.UserNotActivatedException;
import com.hzy.mydemo.modules.firstversion.rest.vm.LoginVM;
import com.hzy.mydemo.modules.login.security.AuthoritiesConstants;
import com.hzy.mydemo.modules.login.security.jwt.JWTFilter;
import com.hzy.mydemo.modules.login.security.jwt.TokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
//用于类上，生成的api文档会根据tags分类，直白的说就是这个controller中的所有接口生成的接口文档都会在tags这个list下；tags如果有多个值，会生成多个list，每个list都显示所有接口
//value的作用类似tags,但是不能有多个值（测试过，使用value没有显示）
@Api(tags = "登录")
public class UserJWTController {

    private final TokenProvider tokenProvider;
    private final UsersRepository usersRepository;
    private final AccountsRepository accountsRepository;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public UserJWTController(
        TokenProvider tokenProvider,
        UsersRepository usersRepository,
        AccountsRepository accountsRepository,
        AuthenticationManagerBuilder authenticationManagerBuilder
    ) {
        this.tokenProvider = tokenProvider;
        this.usersRepository = usersRepository;
        this.accountsRepository = accountsRepository;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    /*
     *相当于签发jwt
     */
    @PostMapping("/authenticate")
    @ApiOperation(value = "账号密码登录")
    //swagger中的注解 ，用来对某个方法/接口进行描述 @ApiOperation(value = “接口说明”, httpMethod = “接口请求方式”, response = “接口返回参数类型”, notes = “接口发布说明”
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM) {
        accountsRepository
            .findByOpenIdAndCategoriesAndDeletedIsFalse(loginVM.getUsername().trim(), AuthoritiesConstants.CATEGORY_LOGIN)
            .map(
                accounts -> {
                    Optional<Users> users = usersRepository.findByIdAndState(accounts.getUserId(), AuthoritiesConstants.STATE_NORMAL);
                    if (!users.isPresent()) {
                        throw new BadRequestAlertException("账号已停用");
                    }
                    return accounts;
                }
            )
            .orElseThrow(() -> new BadRequestAlertException("账号不存在或密码错误，请重新输入"));

        try {
            //1.传入用户名和密码创建了一个UsernamePasswordAuthenticationToken对象，这是我们前面说过的Authentication的实现类，
            // 传入用户名和密码做构造参数，这个对象就是我们创建出来的未认证的Authentication对象。 在这个类中存在两个属性：principal和credentials，其实分别代表着用户和密码
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginVM.getUsername().trim(),
                loginVM.getPassword()
            );

            //2.使用我们先前已经声明过的Bean-authenticationManager调用它的authenticate方法进行认证，返回一个认证完成的Authentication对象。(认证成功后AuthenticationManager返回一个完整填充的Authentication实例)
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            //3.认证完成没有出现异常，就会走到第三步，使用SecurityContextHolder获取SecurityContext之后，将认证完成之后的Authentication对象，放入上下文对象。
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication, loginVM.isRememberMe());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            throw new BadRequestAlertException("账号或密码错误");
        }
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
