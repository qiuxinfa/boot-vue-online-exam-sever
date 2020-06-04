package com.qxf.security.config;

import com.qxf.entity.User;
import com.qxf.security.property.SecurityProperties;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName TokenFilter
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/6 22:54
 **/
@Component
public class TokenFilter extends GenericFilterBean {

    private TokenProvider tokenProvider;
    private SecurityProperties properties;

    TokenFilter(TokenProvider tokenProvider,SecurityProperties properties) {
        this.tokenProvider = tokenProvider;
        this.properties = properties;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = resolveToken(httpServletRequest);
        // 请求头的用户名
        String currentUsername = httpServletRequest.getHeader("currentUsername");

        String requestRri = httpServletRequest.getRequestURI();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 如果上下文没有用户信息，并且token有效（排除了登录，因为登录是没有token的），
        if (StringUtils.hasText(token) && tokenProvider.validateToken(token,currentUsername)) {
            //从token中获取用户信息
            if (authentication == null){
                authentication = tokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("set Authentication to security context for '{"+authentication.getName()+"}'," +
                        " uri: {"+requestRri+"}");
            }
        } else {
            System.out.println("no valid JWT token found, uri: {"+requestRri+"}");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /*
     * @Author qiuxinfa
     * @Description 解析token
     * @Date  2020/5/10 22:56
     * @Param [request]
     * @return java.lang.String
     **/
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(properties.getHeader());
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(properties.getTokenStartWith())) {
            // 去掉令牌前缀
            return bearerToken.replace(properties.getTokenStartWith(),"");
        }
        return null;
    }
}
