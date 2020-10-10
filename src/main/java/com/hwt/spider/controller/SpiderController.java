package com.hwt.spider.controller;

import com.hwt.spider.entity.param.SpiderFictionParam;
import com.hwt.spider.result.Result;
import com.hwt.spider.result.ReturnResult;
import com.hwt.spider.service.SpiderFictionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author hwt
 * @Date:13:53 2020/9/25
 * @Description
 */
@RestController
@RequestMapping("/fiction")
@Api(value = "小说接口", tags = "小说接口")
@CrossOrigin
public class SpiderController {

    @Resource
    private SpiderFictionService spiderFictionService;

    @RequestMapping(value= "/list",produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "小说列表", notes = "小说列表")
    public Result list(@RequestParam String keyword){
        System.out.println(keyword);
        return ReturnResult.OK(spiderFictionService.getList(keyword));
    }
}
