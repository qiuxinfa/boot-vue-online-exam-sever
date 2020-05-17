package com.qxf.util;

/**
 * @ClassName EnumCode
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2020/5/17 10:24
 **/
public enum EnumCode {
    /**
     * 200请求成功
     */
    OK(200),
    /**
     * 303登录失败
     */
    LOGIN_FAIL(303),
    /**
     * 400请求参数出错
     */
    BAD_REQUEST(400),
    /**
     * 401没有登录
     */
    UNAUTHORIZED(401),
    /**
     * 403没有权限
     */
    FORBIDDEN(403),
    /**
     * 410已被删除
     */
    GONE(410),
    /**
     * 423已被锁定
     */
    LOCKED(423),
    /**
     * 500服务器出错
     */
    INTERNAL_SERVER_ERROR(500),
    /**
     * 异常
     */
    EXCPTION_ERROR(4001);

    private final Integer value;

    private EnumCode(Integer value) {
        this.value = value;
    }

    /**
     * 获取value
     */
    public Integer getValue() {
        return this.value;
    }
}
