package com.hzy.mydemo.config;

import com.hzy.mydemo.modules.login.security.AuthoritiesConstants;
import com.hzy.mydemo.modules.login.security.jwt.JWTConfigurer;
import com.hzy.mydemo.modules.login.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;
import tech.jhipster.config.JHipsterProperties;

@EnableWebSecurity //启用Spring Security的Web安全支持，并提供Spring MVC集成
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class) //一个类库提供了很多bean，在我们这个配置里面我只关心其中一个或者几个,那我就使用import来把需要的列出来，依赖注入的时候，如果发现这个bean没有就会明确告诉你，而不是说过一大坨bean都找不到
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JHipsterProperties jHipsterProperties;

    private final TokenProvider tokenProvider;

    private final CorsFilter corsFilter;
    private final SecurityProblemSupport problemSupport; //添加SecurityProblemSupport来处理安全异常捕捉

    public SecurityConfiguration(
        TokenProvider tokenProvider,
        CorsFilter corsFilter,
        JHipsterProperties jHipsterProperties,
        SecurityProblemSupport problemSupport
    ) {
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.problemSupport = problemSupport;
        this.jHipsterProperties = jHipsterProperties;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     *通过覆盖实现该方法，开发人员可以定制WebSecurity,主要是除了HttpSecurity之外的安全控制，比如忽略某些静态公开资源或者动态公开资源的安全 ,
     * 设置需要使用的防火墙实例，设置权限评估器，安全表达式处理器等;
     */
    @Override
    public void configure(WebSecurity web) { //web安全
        web
            .ignoring()
            .antMatchers(HttpMethod.OPTIONS, "/**")
            .antMatchers("/app/**/*.{js,html}")
            .antMatchers("/i18n/**")
            .antMatchers("/content/**")
            .antMatchers("/swagger-ui/**")
            .antMatchers("/test/**");
    }

    /*
     *该方法定义了哪些路径应该被保护，哪些路径不需要保护
     * primitAll()用户可任意访问
     * authenticated()用户登录后可访问
     * hasAuthority(String)如果用户有参数，则其权限可访问
     * hasRole("权限")则是允许这个url给与参数中相等的权限访问。
     * access("hasRole('权限') and hasRole('权限')") 是指允许访问这个url必须同时拥有参数中多个身份权限才可以访问
     * hasAnyRole("ADMIN", "DBA")是指允许访问这个url必须同时拥有参数中多个身份权限中的一个就可以访问该url
     */
    @Override
    public void configure(HttpSecurity http) throws Exception { //http请求安全处理
        // @formatter:off
        http
            .csrf()
            .disable()  //关闭csrf防护 如果没有这行代码导致用户无法被认证 https://blog.csdn.net/donglinjob/article/details/109057312
            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)//将corsFilter拦截器添加到UsernamePasswordAuthenticationFilter拦截器之前
            .exceptionHandling()
                .authenticationEntryPoint(problemSupport) //用来解决匿名用户访问无权限资源时的异常
                .accessDeniedHandler(problemSupport) //用来解决认证过的用户访问无权限资源时的异常
        .and()
            .headers()
            .contentSecurityPolicy(jHipsterProperties.getSecurity().getContentSecurityPolicy())//TODO 内容安全策略？？？？
        .and()
            .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)//strict-origin-when-cross-origin对于同源的请求，会发送完整的URL作为引用地址；在同等安全级别的情况下，发送文件的源作为引用地址(HTTPS->HTTPS)；在降级的情况下不发送此首部 (HTTPS->HTTP)。
        .and()
            //Feature Policy是一个新的http响应头属性，允许一个站点开启或者禁止一些浏览器属性和API，来更好的确保站点的安全性和隐私性。 可以严格的限制站点允许使用的属性是很愉快的，而可以对内嵌在站点中的iframe进行限制则更加增加了站点的安全性。
            .featurePolicy("geolocation 'none'; midi 'none'; sync-xhr 'none'; microphone 'none'; camera 'none'; magnetometer 'none'; gyroscope 'none'; fullscreen 'self'; payment 'none'")
        .and()
            .frameOptions()//https://www.jianshu.com/p/9ec724f4e3ae
            .deny()//禁止iframe调用 表示该页面不允许在 frame 中展示，即便是在相同域名的页面中嵌套也不允许。
        .and()
            .sessionManagement()
            //通常称为无状态的。为啥要关注这个stateless无状态的情况的呢？因为目前，我们的应用基本都是前后端分离的应用。比方说，你的一套java api是给react前端、安卓端、IOS端 调用的。这个时候你还提什么session啊，这时候我们需要的是无状态，通常以一种token的方式来交互。
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//stateless不创建不使用session
        .and()
            .authorizeRequests()//我们可以在authorizeRequests() 后定义多个antMatchers()配置器来控制不同的url接受不同权限的用户访问
            .antMatchers("/api/authenticate").permitAll()
            .antMatchers("/api/register").permitAll()
            .antMatchers("/api/activate").permitAll()
            .antMatchers("/api/account/reset-password/init").permitAll()
            .antMatchers("/api/account/reset-password/finish").permitAll()
            .antMatchers("/api/admin/**").hasAuthority(AuthoritiesConstants.ADMIN)
            .antMatchers("/api/**").authenticated()
            .antMatchers("/management/health").permitAll()
            .antMatchers("/management/health/**").permitAll()
            .antMatchers("/management/info").permitAll()
            .antMatchers("/management/prometheus").permitAll()
            .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
        .and()
            //此处就是doc.html文档中的全局参数设置添加 Authorization参数，value为Basic xxx，xxx就是生成的base64编码。 https://blog.csdn.net/wangb_java/article/details/86502166
            .httpBasic()//httpBasic是由http协议定义的最基础的认证方式。每次请求时，在请求头Authorization参数中附带用户/密码的base64编码
        .and()
            .apply(securityConfigurerAdapter()); //这里增加securityConfigurerAdapter
        // @formatter:on
    }

    //    //重写了configure参数为AuthenticationManagerBuilder的方法
    //    @Override
    //    protected void configure(AuthenticationManagerBuilder auth) throws Exception { //身份验证管理生成器
    //        super.configure(auth);
    //    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }
}
