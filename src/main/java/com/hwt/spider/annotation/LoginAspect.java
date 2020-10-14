package com.hwt.spider.annotation;


import com.hwt.spider.service.LoginValidationService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: hwt
 * @date: 2020/10/3 19:53
 * @description: 验证登录的切面类
 */
@Aspect
@Component
public class LoginAspect {

    @Resource
    private LoginValidationService loginValidationService;

    @Pointcut("execution(public * com.hwt.spider.controller.*.*(..)) && @annotation(com.hwt.spider.annotation.Login)")
    public void pointcut(){}

    @Around("pointcut()")
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {
        //loginValidationService.verifyLoginUser(pjp);
        //继续
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throw throwable;
        }
    }

}
