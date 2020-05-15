package com.qxf.security.config;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName JwtAuthenticationEntryPoint
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/8 20:04
 **/
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // 当用户尝试访问安全的REST资源而不提供任何凭据时，将调用此方法发送401 响应

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "登录信息已过期，请重新登录！");
//        String requestURI = request.getRequestURI();
//        String token = request.getParameter("token");
//        String username = request.getParameter("username");
        // return "forword:/ceng/hello.html";
//        if (token != null && username != null){
//            response.sendRedirect("/user/refreshToken?token="+token+"&username="+username);
//        }else {
//
//        }

    }


}

