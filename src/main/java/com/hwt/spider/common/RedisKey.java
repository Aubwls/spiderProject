package com.hwt.spider.common;

/**
 * @Author hwt
 * @Date:11:11 2020/10/15
 * @Description
 */
public class RedisKey {

    private static final String LOGIN_TOKEN_KEY = "LOGIN_TOKEN_KEY:";

    private static final String LOGIN_TOKEN_VALUE = "LOGIN_TOKEN_VALUE:";

    public static String getLoginTokenKey (String userId){
        return LOGIN_TOKEN_KEY + userId;
    }
    public static String getLoginTokenValue (String token){
        return LOGIN_TOKEN_VALUE + token;
    }
}
