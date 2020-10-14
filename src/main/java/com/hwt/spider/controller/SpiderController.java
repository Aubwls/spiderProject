package com.hwt.spider.controller;

import com.hwt.spider.entity.param.SpiderFictionParam;
import com.hwt.spider.result.Result;
import com.hwt.spider.result.ReturnResult;
import com.hwt.spider.service.SpiderFictionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
public class SpiderController {

    @Resource
    private SpiderFictionService spiderFictionService;

    @RequestMapping(value= "/list", method = RequestMethod.POST)
    @ApiOperation(value = "小说列表", notes = "小说列表")
    public Result list(@RequestBody SpiderFictionParam spiderFictionParam){
        return ReturnResult.OK(spiderFictionService.getList(spiderFictionParam.getKeyword()));
    }
}
