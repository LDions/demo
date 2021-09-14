package com.hzy.mydemo.modules.login.security.jwt;

import com.hzy.mydemo.modules.login.security.UserModel;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import tech.jhipster.config.JHipsterProperties;

@Component
public class TokenProvider {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";
    private static final String CURRENTUSER_KEY = "cur";

    private final Key key;

    private final JwtParser jwtParser;

    private final long tokenValidityInMilliseconds;

    private final long tokenValidityInMillisecondsForRememberMe;

    public TokenProvider(JHipsterProperties jHipsterProperties) {
        byte[] keyBytes;
        String secret = jHipsterProperties.getSecurity().getAuthentication().getJwt().getSecret();
        if (!ObjectUtils.isEmpty(secret)) {
            log.warn(
                "Warning: the JWT key used is not Base64-encoded. " +
                "We recommend using the `jhipster.security.authentication.jwt.base64-secret` key for optimum security."
            );
            keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        } else {
            log.debug("Using a Base64-encoded JWT secret key");
            keyBytes = Decoders.BASE64.decode(jHipsterProperties.getSecurity().getAuthentication().getJwt().getBase64Secret());
        }
        key = Keys.hmacShaKeyFor(keyBytes);
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
        this.tokenValidityInMilliseconds = 1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds();
        this.tokenValidityInMillisecondsForRememberMe =
            1000 * jHipsterProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSecondsForRememberMe();
    }

    //    public static void main(String[] args) {
    //
    //        Map<String, Object> map = new HashMap<>();
    //        map.put("name","cc");
    //        map.put("phone","15044042528");
    //         String jwt=Jwts
    //            .builder()
    //            .setSubject("title") //代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
    //            .claim(AUTHORITIES_KEY, map) //声明自定义属性
    //            .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
    //            .compact();
    //         System.out.println(jwt);
    //    }

    /*
     *创建token
     */
    public String createToken(Authentication authentication, boolean rememberMe) {
        UserModel userModel = (UserModel) authentication.getPrincipal();

        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", userModel.getId());
        map.put("name", userModel.getName());
        map.put("phone", userModel.getPhone());
        return Jwts
            .builder()
            .setSubject(authentication.getName()) //代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
            .claim(AUTHORITIES_KEY, map) //声明自定义属性
            .signWith(key, SignatureAlgorithm.HS512) //设置签名使用的签名算法和签名使用的私钥
            .setExpiration(validity) //设置过期时间
            .compact(); //将其压缩成最终string形式,生成最终的jws
        //        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        //
        //        long now = (new Date()).getTime();
        //        Date validity;
        //        if (rememberMe) {
        //            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        //        } else {
        //            validity = new Date(now + this.tokenValidityInMilliseconds);
        //        }
        //
        //        return Jwts
        //            .builder()
        //            .setSubject(authentication.getName())
        //            .claim(AUTHORITIES_KEY, authorities)
        //            .signWith(key, SignatureAlgorithm.HS512)
        //            .setExpiration(validity)
        //            .compact();
    }

    /*
     *解析token
     */
    public Authentication getAuthentication(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays
            .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
            .filter(auth -> !auth.trim().isEmpty())
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    /*
     *验证token
     */
    public boolean validateToken(String authToken) {
        try {
            jwtParser.parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace.", e);
        }
        return false;
    }
}
