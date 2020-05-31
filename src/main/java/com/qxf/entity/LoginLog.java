package com.qxf.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName LoginLog
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/31 19:07
 **/
public class LoginLog implements Serializable{
    private String id;
    private String userId;
    private Date loginTime;
    private String ip;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
