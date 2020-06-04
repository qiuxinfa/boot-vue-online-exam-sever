package com.qxf.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserInfoUtil
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/6/1 22:36
 **/
public class UserInfoUtil {
    public static Map<String,String> userIdMap = new HashMap<>();
    public static Map<String,String> passwordMap = new HashMap<>();

    public static void setUserIdByUsername(String username,String userId){
        userIdMap.put(username,userId);
    }

    public static String getUserIdByUsername(String username){
        return userIdMap.get(username);
    }

    public static void setPasswordByUsername(String username,String password){
        passwordMap.put(username,password);
    }

    public static String getPasswordByUsername(String username){
        return passwordMap.get(username);
    }

}
