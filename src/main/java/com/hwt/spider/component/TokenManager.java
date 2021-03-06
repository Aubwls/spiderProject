package com.hwt.spider.component;

/**
 * @Author hwt
 * @Date:10:38 2020/9/28
 * @Description
 */

import com.alibaba.fastjson.JSON;
import com.hwt.spider.common.RedisKey;
import com.hwt.spider.entity.pojo.SpiderUser;
import com.hwt.spider.mapper.SpiderUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class TokenManager {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private SpiderUserMapper spiderUserMapper;

    public String createToken(Long userId){
        checkToken(userId);
        String token = UUID.randomUUID().toString().replace("-","");
        redisTemplate.boundValueOps(RedisKey.getLoginTokenKey(userId)).set(token, 2, TimeUnit.HOURS);
        SpiderUser spiderUser = spiderUserMapper.selectByPrimaryKey(userId);
        String jsonObject = JSON.toJSONString(spiderUser);
        redisTemplate.boundValueOps(RedisKey.getLoginTokenValue(token)).set(jsonObject, 2, TimeUnit.HOURS);
        return userId + "-" + token;
    }

    private void checkToken(Long userId){
        String token = redisTemplate.boundValueOps(RedisKey.getLoginTokenKey(userId)).get();
        if (token != null){
            redisTemplate.delete(RedisKey.getLoginTokenValue(token));
        }
    }
}
