package com.hwt.spider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwt.spider.common.RedisKey;
import com.hwt.spider.exception.BusinessException;
import com.hwt.spider.service.LoginValidationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void estimateLogin() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            log.error("账号未登录！");
            throw new BusinessException(401,"账号未登录！");
        }
//        String[] split = token.split("-");
//        Long userId = Long.parseLong(split[0]);
//        String realToken = split[1];
//        Object tokenJson = redisTemplate.boundValueOps(RedisKey.getLoginTokenKey(userId)).get();
//        JSONObject objectSession = JSONObject.parseObject(JSON.toJSONString(tokenJson));
//        log.info("objectSession="+objectSession);
//        if (objectSession == null || objectSession.get("userId") == null) {
//            log.info("登录失效");
//            throw new BusinessException(401,"登录失效");
//        }
//        //获得用户信息
//        Object userInfo = redisTemplate.boundValueOps(RedisKey.getLoginTokenValue(checkToken.toString())).get();
//        if (userInfo == null) {
//            log.error("用户信息不存在！");
//            throw new BusinessException(401,"登录失效！");
//        }
    }
}
