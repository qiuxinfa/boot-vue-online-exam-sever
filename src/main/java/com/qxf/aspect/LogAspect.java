package com.qxf.aspect;

import com.qxf.annotation.MyLog;
import com.qxf.dao.OperateLogDao;
import com.qxf.dao.UserDao;
import com.qxf.entity.OperateLog;

import com.qxf.util.HttpContextUtil;
import com.qxf.util.IPUtil;
import com.qxf.util.UserInfoUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName LogAspect
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/31 18:23
 **/
@Component
@Aspect
public class LogAspect {
    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private OperateLogDao operateLogDao;

    @Autowired
    private UserDao userDao;

    @Pointcut("@annotation(com.qxf.annotation.MyLog)")
    public void myPointcut(){

    }

    @Around("myPointcut()")
    public Object myAround(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyLog logAnnotation = method.getAnnotation(MyLog.class);
        if (logAnnotation != null && logAnnotation.value()) {
            // 请求的方法参数值
            Object[] args = joinPoint.getArgs();
            // 请求的方法参数名称
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            String[] paramNames = u.getParameterNames(method);
            String params = "";
            if (args != null && paramNames != null) {
                for (int i = 0; i < args.length; i++) {
                    params += "  " + paramNames[i] + ": " + args[i];
                }
            }

            // 获取request
            HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
            // 请求头的用户名
            String currentUsername = request.getHeader("currentUsername");
            logger.info("执行请求 {} 耗时 {} 毫秒",request.getRequestURI(),time);
            OperateLog sysLog = new OperateLog();
            sysLog.setId(UUID.randomUUID().toString().replace("-",""));
            sysLog.setRequestUrl(request.getRequestURI());
            sysLog.setRemoteAddr(IPUtil.getIPAddress(request));
            sysLog.setParams(params);
            sysLog.setMethod(request.getMethod());
            sysLog.setCreateTime(new Date());
            sysLog.setUserId(UserInfoUtil.getUserIdByUsername(currentUsername));
            sysLog.setIsSuccess(1);
            // 保存操作日志
            operateLogDao.addOperateLog(sysLog);
        }
    }

}
