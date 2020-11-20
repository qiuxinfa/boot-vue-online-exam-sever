package com.qxf.security.config;

import com.qxf.dao.PermissionDao;
import com.qxf.entity.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @ClassName BaseUrlControl
 * @Description 实现基于url的访问控制
 * @Author qiuxinfa
 * @Date 2020/5/31 8:30
 **/
@Component
public class BaseUrlControl {

    @Autowired
    private PermissionDao permissionDao;

    private static Logger logger = LoggerFactory.getLogger(BaseUrlControl.class);
    /**
     * 判断请求的url是否有权访问
     */
    public boolean canAccess(HttpServletRequest request, Authentication authentication) {
        boolean flag = false;
        // 请求url
        String url = request.getServletPath();
        String pathInfo = request.getPathInfo();
        if (pathInfo != null) {
            url = StringUtils.hasLength(url) ? url + pathInfo : pathInfo;
        }
        //如果没有通过认证，则不能访问， anonymousUser是springSecurity放入的匿名用户
        Object principal = authentication.getPrincipal();
        if(principal == null || "anonymousUser".equals(principal)) {
            logger.info("认证未通过，无权访问 {} ",url);
            return flag;
        }

        //匿名访问的url，在之前配置了，所以这里不通过
        if(authentication instanceof AnonymousAuthenticationToken){
            logger.info("认证未通过，无权访问 {} ",url);
            return flag;
        }

        //用户拥有的权限集合，也就是在UserServiceImpl放入的权限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        //URL规则匹配，判断用户是否权限集合中，是否包含请求的url
        AntPathRequestMatcher matcher;
        String resUrl = "";
        if (authorities != null && authorities.size() > 0){
            for(GrantedAuthority grantedAuthority : authorities) {
                resUrl = grantedAuthority.getAuthority();
                matcher = new AntPathRequestMatcher(resUrl);
                if(matcher.matches(request)) {
                    //匹配成功，返回true
                    flag = true;
                    logger.info("请求的 {} 有权限访问",url);
                    return flag;
                }
            }
        }
        Permission permission = new Permission();
        permission.setUrl(url);
        if (CollectionUtils.isEmpty(permissionDao.queryAll(permission))){
            // 如果数据库没有配置权限，则默认有权限访问
            logger.info("数据库没有配置该url权限，默认有权限访问 {} ",url);
            return true;
        }
        logger.info("没有权限访问 {} ",url);

        return flag;
    }
}
