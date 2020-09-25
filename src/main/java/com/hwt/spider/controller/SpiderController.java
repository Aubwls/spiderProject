package com.hwt.spider.controller;

import com.hwt.spider.entity.param.SpiderFictionParam;
import com.hwt.spider.entity.pojo.Result;
import com.hwt.spider.result.RetureResult;
import com.hwt.spider.service.SpiderFictionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author hwt
 * @Date:13:53 2020/9/25
 * @Description
 */
@RestController
@RequestMapping("/fiction")
@Api(value = "小说接口", tags = "小说接口")
public class SpiderController {

    @Resource
    private SpiderFictionService spiderFictionService;

    @PostMapping("/list")
    @ApiOperation(value = "小说列表", notes = "小说列表")
    public Result list(
            @ApiParam(value = "{\"keyword\":\"关键字\"}") @RequestBody SpiderFictionParam spiderFictionParam){
        return RetureResult.OK(spiderFictionService.getList(spiderFictionParam.getKeyword()));
    }
}
