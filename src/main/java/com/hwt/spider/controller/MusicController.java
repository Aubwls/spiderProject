package com.hwt.spider.controller;

import com.hwt.spider.bean.param.SpiderMusicParam;
import com.hwt.spider.bean.pojo.Result;
import com.hwt.spider.result.RetureResult;
import com.hwt.spider.service.SpiderMusicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: hwt
 * @date: 2020/9/24 23:13
 * @description:
 */
@RestController
@RequestMapping("/music")
@ApiOperation(value = "音乐接口", notes = "音乐接口")
public class MusicController {
    @Resource
    private SpiderMusicService spiderMusicService;

    @PostMapping("/list")
    @ApiOperation(value = "音乐列表")
    public Result list(@ApiParam(value = "{\"keyword\":\"关键字\"}") @RequestBody SpiderMusicParam spiderMusicParam){
        spiderMusicService.list(spiderMusicParam.getKeyword());
        return RetureResult.OK();
    }
}
