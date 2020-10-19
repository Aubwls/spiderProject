package com.hwt.spider.service;

import com.hwt.spider.entity.param.CodeParam;
import com.hwt.spider.entity.param.LoginParam;

/**
 * @Author hwt
 * @Date:11:02 2020/9/28
 * @Description
 */
public interface SpiderLoginService {
    String login(LoginParam loginParam);

    void getCode(CodeParam codeParam);

    void register(LoginParam loginParam);

}
