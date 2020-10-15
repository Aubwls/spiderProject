package com.hwt.spider.service.impl;

import com.hwt.spider.common.RedisKey;
import com.hwt.spider.exception.BusinessException;
import com.hwt.spider.service.LoginValidationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: hwt
 * @date: 2020/10/3 19:53
 * @description:
 */
@Slf4j
@Service
public class LoginValidationServiceImpl implements LoginValidationService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void estimateLogin() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userId = request.getParameter("userId");
        if (StringUtils.isEmpty(userId)) {
            log.error("账号未登录！");
            throw new BusinessException(401,"账号未登录！");
        }
        Object token = redisTemplate.boundValueOps(RedisKey.getLoginTokenKey(userId)).get();
        if (token == null) {
            log.error("token不存在！用户为："+userId);
            throw new BusinessException(401,"登录失效！");
        }
        //获得用户信息
        Object userInfo = redisTemplate.boundValueOps(RedisKey.getLoginTokenValue(token.toString())).get();
        if (userInfo == null) {
            log.error("用户信息不存在！用户为："+userId);
            throw new BusinessException(401,"登录失效！");
        }
    }
}
