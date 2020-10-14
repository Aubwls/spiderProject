package com.hwt.spider.annotation;

import java.lang.annotation.*;

/**
 * @author: hwt
 * @date: 2020/10/3 19:53
 * @description: 验证登录状态
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Login {
}
