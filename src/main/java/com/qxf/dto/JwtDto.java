package com.qxf.dto;

/**
 * @ClassName JwtDto
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/17 11:32
 **/
public class JwtDto {
    private String token;
    private String username;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
