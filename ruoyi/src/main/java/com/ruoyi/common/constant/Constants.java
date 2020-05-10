package com.ruoyi.common.constant;

import io.jsonwebtoken.Claims;

/**
 * 通用常量信息
 * 
 * @author ruoyi
 */
public class Constants
{
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";
    
    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 用户ID
     */
    public static final String JWT_USERID = "userid";

    /**
     * 用户名称
     */
    public static final String JWT_USERNAME = Claims.SUBJECT;

    /**
     * 用户头像
     */
    public static final String JWT_AVATAR = "avatar";

    /**
     * 创建时间
     */
    public static final String JWT_CREATED = "created";

    /**
     * 用户权限
     */
    public static final String JWT_AUTHORITIES = "authorities";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";
    public static final String TIMEOUT_EXPRESS = "120m";
    public static final String QR_PAY_MODE = "";

    /**
     * 订单状态（0已下单未支付，1已支付待发货，2已发货待签收，3已签收，4已关闭，5待退款，6已退款）
     */
    public static final String ORDER_STATUS_UNPAY = "0";
    public static final String ORDER_STATUS_UNDELIVERY = "1";
    public static final String ORDER_STATUS_UNSIGN = "2";
    public static final String ORDER_STATUS_SIGNED = "3";
    public static final String ORDER_STATUS_CLOSED = "4";
    public static final String ORDER_STATUS_WAIT_REFUND = "5";
    public static final String ORDER_STATUS_REFUNDED = "6";
}
