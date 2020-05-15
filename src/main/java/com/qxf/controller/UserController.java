package com.qxf.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qxf.pojo.JwtVo;
import com.qxf.pojo.User;
import com.qxf.security.config.TokenProvider;
import com.qxf.security.property.SecurityProperties;
import com.qxf.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @ClassName UserController
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/6 20:37
 **/
@RestController
@RequestMapping("user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/list")
    public ResponseEntity<Object> getAllUser(){
        Map<String,Object> authInfo = new HashMap<String,Object>(2){{
            put("items",userService.getUserList());
        }};
        return ResponseEntity.ok(authInfo);
    }

    //用户登录
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user, HttpServletRequest request,
                                        HttpServletResponse response) throws JsonProcessingException {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        //认证
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌
        String jwtToken = tokenProvider.createToken(authentication);
        // 返回 token 与 用户信息
        Map<String,Object> authInfo = new HashMap<String,Object>(3){{
            put("token", securityProperties.getTokenStartWith() + jwtToken);
            put("tokenExpiredTime",new Date().getTime() + securityProperties.getTokenValidityInSeconds());
            put("user",objectMapper.writeValueAsString(authentication.getPrincipal()));
        }};
        logger.info(user.getUsername()+" ：登录成功");
        return ResponseEntity.ok(authInfo);
    }

    @GetMapping("/info")
    public ResponseEntity<Object> getUserInfo(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String,Object> authInfo = new HashMap<String,Object>(2){{
            put("name",userDetails == null ? "未知用户" : userDetails.getUsername());
            put("avatar", "");
        }};
        return ResponseEntity.ok(authInfo);
    }

    //刷新token  1589105115025
    @PostMapping("/refreshToken")
    public ResponseEntity<Object> refreshToken(@RequestBody JwtVo jwtVo) throws JsonProcessingException {
        Map<String,Object> authInfo = new HashMap<>(3);
        String token = jwtVo.getToken();
        String username = jwtVo.getUsername();
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
            authInfo.put("user",objectMapper.writeValueAsString(authentication.getPrincipal()));
        }else {
            logger.info("请求刷新的token无效："+token);
            authInfo.put("token","");
            authInfo.put("tokenExpiredTime","");
            authInfo.put("user","");
        }

        return ResponseEntity.ok(authInfo);
    }

}
