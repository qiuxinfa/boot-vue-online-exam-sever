package com.qxf.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qxf.dto.JwtDto;
import com.qxf.entity.User;
import com.qxf.security.config.TokenProvider;
import com.qxf.security.property.SecurityProperties;
import com.qxf.service.UserService;
import com.qxf.util.EnumCode;
import com.qxf.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/29 23:51
 **/
@RestController
@RequestMapping("auth")
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private SecurityProperties securityProperties;

    //用户登录
    @PostMapping("/login")
    public ResultUtil login(@RequestBody User user, HttpServletRequest request,
                            HttpServletResponse response) throws JsonProcessingException {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = null;
        //认证
        try {
            authentication = authenticationManagerBuilder.getObject().authenticate(token);
        }catch (DisabledException e){
            System.out.println("账号被禁止登录。。。。");
            return new ResultUtil(EnumCode.INTERNAL_SERVER_ERROR.getValue(),"账号已被禁止登录，请联系管理员！");
        }catch (Exception e){
            System.out.println("其他认证异常。。");
            return new ResultUtil(EnumCode.INTERNAL_SERVER_ERROR.getValue(),"用户名或密码错误！");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌
        String jwtToken = tokenProvider.createToken(authentication);
        // 返回 token 与 用户信息
        Map<String,Object> authInfo = new HashMap<>(3);
        authInfo.put("token", securityProperties.getTokenStartWith() + jwtToken);
        authInfo.put("tokenExpiredTime",new Date().getTime() + securityProperties.getTokenValidityInSeconds());
        authInfo.put("user",authentication.getPrincipal());
        logger.info(user.getUsername()+" ：登录成功");
        return new ResultUtil(EnumCode.OK.getValue(),"登录成功！",authInfo);
    }

    //刷新token
    @PostMapping("/refreshToken")
    public ResultUtil refreshToken(@RequestBody JwtDto jwtDto) throws JsonProcessingException {
        Map<String,Object> authInfo = new HashMap<>(3);
        String token = jwtDto.getToken();
        String username = jwtDto.getUsername();
        //检查token格式
        if (token != null && token.startsWith(securityProperties.getTokenStartWith())){
            //去掉头部
            token = token.replace(securityProperties.getTokenStartWith(),"");
            logger.info("开始刷新token当前时间 {} ms",new Date().getTime() + securityProperties.getTokenValidityInSeconds());
            //返回新的token
//            try {
//                tokenProvider.validateRefreshToken(token);
//            }catch (ExpiredJwtException e){
//                //token过期
//                logger.info("refreshToken，token已过期");
//
//            }
            //认证
//            User user = userService.getUserByUsername(username);
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),"123456");
//            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            // 生成新的令牌
            String jwtToken = tokenProvider.createToken(authentication);

            authInfo.put("token", securityProperties.getTokenStartWith() + jwtToken);
            authInfo.put("tokenExpiredTime",new Date().getTime() + securityProperties.getTokenValidityInSeconds());
            authInfo.put("user",authentication.getPrincipal());
        }else {
            logger.info("请求刷新的token无效："+token);
            authInfo.put("token","");
            authInfo.put("tokenExpiredTime","");
            authInfo.put("user","");
        }

        return new ResultUtil(EnumCode.OK.getValue(),"刷新token成功！",authInfo);
    }

}
