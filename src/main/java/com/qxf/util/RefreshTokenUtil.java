package com.qxf.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qxf.security.config.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @ClassName RefreshTokenUtil
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/6/1 21:07
 **/
@Component
public class RefreshTokenUtil {
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private TokenProvider tokenProvider;

    //刷新token
    public String refreshToken(String username,String password){
        //认证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成新的令牌
        String jwtToken = tokenProvider.createToken(authentication);
        return jwtToken;
    }
}
