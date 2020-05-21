package com.qxf.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.Page;
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

    @GetMapping("/list")
    public Object getListByPage(Integer startPage,Integer pageSize,String username){
        Page<User> page = new Page<>(startPage,pageSize);
        //查询自己的考试记录
        List<User> list = userService.getListByPage(page,username);
        return new ResultUtil(EnumCode.OK.getValue(),"请求成功",list,page.getTotal());
    }

    @PostMapping("/add")
    public ResultUtil addUser(@RequestBody User user){
        String msg = "新增失败！";
        Integer cnt = userService.addUser(user);
        if (cnt > 0){
            msg = "新增成功！";
        }
        return new ResultUtil(EnumCode.OK.getValue(),msg);
    }

    @PostMapping("/update")
    public ResultUtil updateUser(@RequestBody User user){
        String msg = "修改失败！";
        Integer cnt = userService.updateUser(user);
        if (cnt > 0){
            msg = "修改成功！";
        }
        return new ResultUtil(EnumCode.OK.getValue(),msg);
    }

    @PostMapping("/delete")
    public ResultUtil deleteUser(String id){
        String msg = "删除失败！";
        Integer cnt = userService.deleteUser(id);
        if (cnt > 0){
            msg = "删除成功！";
        }
        return new ResultUtil(EnumCode.OK.getValue(),msg);
    }
    //用户登录
    @PostMapping("/login")
    public ResultUtil login(@RequestBody User user, HttpServletRequest request,
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
            put("user",authentication.getPrincipal());
        }};
        logger.info(user.getUsername()+" ：登录成功");
        return new ResultUtil(EnumCode.OK.getValue(),"登录成功！",authInfo);
    }

    @GetMapping("/info")
    public ResultUtil getUserInfo(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String,Object> authInfo = new HashMap<String,Object>(2){{
            put("name",userDetails == null ? "未知用户" : userDetails.getUsername());
            put("avatar", "");
        }};
        return new ResultUtil(EnumCode.OK.getValue(),"获取用户信息成功",authInfo);
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
