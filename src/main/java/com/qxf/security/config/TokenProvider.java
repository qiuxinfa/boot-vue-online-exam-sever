package com.qxf.security.config;

import com.qxf.security.property.SecurityProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @ClassName TokenProvider
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/6 22:55
 **/
@Component
public class TokenProvider implements InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private final SecurityProperties properties;
    private static final String AUTHORITIES_KEY = "auth";
    private Key key;
    private Date expiredTime;  //jwt过期时间
    //jwt过期多久后，可以刷新token，单位是ms，默认是5分钟之内再次请求，还可以刷新token，否则重新登录
    private Long refreshInterval = 1000*60*5L;

    public TokenProvider(SecurityProperties properties) {
        this.properties = properties;
    }


    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(properties.getBase64Secret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /*
     * @Author qiuxinfa
     * @Description 创建token
     * @Date  2020/5/10 9:34
     * @Param [authentication]
     * @return java.lang.String
     **/
    public String createToken(Authentication authentication) {
        //权限，这里其实是角色
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        expiredTime = new Date(now + properties.getTokenValidityInSeconds());

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(expiredTime)
                .compact();
    }

    /*
     * @Author qiuxinfa
     * @Description 根据token，获取用户信息
     * @Date  2020/5/10 9:34
     * @Param [token]
     * @return org.springframework.security.core.Authentication
     **/
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        // 当前用户如果没有任何权限时，在输入用户名后，刷新验证码会抛IllegalArgumentException
        Object authoritiesStr = claims.get(AUTHORITIES_KEY);
        Collection<? extends GrantedAuthority> authorities =
                (StringUtils.isEmpty(authoritiesStr))? Collections.emptyList() :
                        Arrays.stream(authoritiesStr.toString().split(","))
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    boolean validateToken(String authToken){
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("Invalid JWT signature.");
            e.printStackTrace();
        } catch (ExpiredJwtException e) {
            logger.info("Expired JWT token.");
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            logger.info("Unsupported JWT token.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            logger.info("JWT token compact of handler are invalid.");
            e.printStackTrace();
        }
        return false;
    }

    /*
     * @Author qiuxinfa
     * @Description 刷新token
     * @Date  2020/5/10 10:01
     * @Param [token]
     * @return java.lang.String
     **/
    public String refresh(String token){
        //刷新之前要检验，当token有效才刷新
        return createToken(getAuthentication(token));
    }

    public boolean mustRefreshToken() {
        Long diffTime = (new Date()).getTime() - expiredTime.getTime();
        //token将要过期5分钟之内刷新token
        return  diffTime >=0 && diffTime <= refreshInterval;
    }

    //检验请求刷新的token
    public boolean validateRefreshToken(String authToken){
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("Invalid JWT signature.");
            e.printStackTrace();
        } catch (ExpiredJwtException e) {
            logger.info("validateRefreshToken jwt token expired");
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            logger.info("Unsupported JWT token.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            logger.info("JWT token compact of handler are invalid.");
            e.printStackTrace();
        }
        return false;
    }
}