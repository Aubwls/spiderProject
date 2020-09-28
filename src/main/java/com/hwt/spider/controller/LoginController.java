package com.hwt.spider.controller;

import com.hwt.spider.entity.param.LoginParam;
import com.hwt.spider.result.Result;
import com.hwt.spider.result.ReturnResult;
import com.hwt.spider.service.SpiderLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @Author hwt
 * @Date:11:03 2020/9/28
 * @Description
 */
@RestController
@RequestMapping("/login")
@Api(value = "登录接口", tags = "登录接口")
public class LoginController {
    @Resource
    private SpiderLoginService spiderLoginService;

    @PostMapping("/login")
    @ApiOperation(value = "登录操作", tags = "登录操作")
    public Result login(@RequestBody LoginParam loginParam){
        String token = spiderLoginService.login(loginParam);
        return ReturnResult.OK(token);
    }
}