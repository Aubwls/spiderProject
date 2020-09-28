package com.hwt.spider.component;

/**
 * @Author hwt
 * @Date:10:38 2020/9/28
 * @Description
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class TokenManager {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String createToken(Long userId){
        String token = userId.toString()+"-"+UUID.randomUUID().toString();
        String key = userId.toString()+"_token";
        System.out.println(redisTemplate);
        redisTemplate.opsForValue().set(key,token,1000*60*60, TimeUnit.SECONDS);
        return token;
    }
    public boolean checkToken(String token){
        //解析出userId和uuid
        if(token==null || "".equals(token)){
            return false;
        }
        String[] arr1 = token.split("_");
        if(arr1.length != 2){
            return false;
        }
        //根据redis进行检查
        String key = arr1[0]+"_token";
        String r_token = (String)redisTemplate.opsForValue().get(key);
        if(r_token==null){
            return false;
        }
        if(!token.equals(r_token)){
            return false;
        }
        //返回检测结果,更新token时间
        redisTemplate.opsForValue().set(key, token,1000*60*60, TimeUnit.SECONDS);
        return true;
    }

    public void clear(Long userId){
        String key = userId.toString()+"_token";
        redisTemplate.delete(key);
    }
}
