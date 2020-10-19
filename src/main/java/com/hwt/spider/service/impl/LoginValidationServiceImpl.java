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
        String token = request.getParameter("token");
        System.out.println(token);
        if (StringUtils.isEmpty(token)) {
            log.error("账号未登录！");
            throw new BusinessException(401,"账号未登录！");
        }

        //获得用户信息
        Object userInfo = redisTemplate.boundValueOps(RedisKey.getLoginTokenValue(token.toString())).get();
        if (userInfo == null) {
            log.error("用户信息不存在！");
            throw new BusinessException(401,"登录失效！");
        }
    }
}
