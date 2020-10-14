package com.hwt.spider.controller;

import com.hwt.spider.entity.param.CodeParam;
import com.hwt.spider.entity.param.LoginParam;
import com.hwt.spider.result.Result;
import com.hwt.spider.result.ReturnResult;
import com.hwt.spider.service.SpiderLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
    public Result<String> login(@RequestBody LoginParam loginParam){
        String token = spiderLoginService.login(loginParam);
        return ReturnResult.OK(token);
    }

    @PostMapping("/getCode")
    @ApiOperation(value = "获取验证码", tags = "获取验证码")
    public Result getCode(@RequestBody CodeParam codeParam){
        spiderLoginService.getCode(codeParam);
        return ReturnResult.OK();
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册", tags = "用户注册")
    public Result register(@RequestBody LoginParam loginParam){
        spiderLoginService.register(loginParam);
        return ReturnResult.OK();
    }

    @PostMapping("/getAccoutNum")
    @ApiOperation(value = "获取账号", tags = "获取账号")
    public Result<String> getAccoutNum(){
        return ReturnResult.OK(spiderLoginService.getAccoutNum());
    }
}
