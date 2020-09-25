package com.hwt.spider.controller;

import com.hwt.spider.entity.param.SpiderMusicParam;
import com.hwt.spider.entity.pojo.Result;
import com.hwt.spider.entity.pojo.SpiderMusic;
import com.hwt.spider.result.RetureResult;
import com.hwt.spider.service.SpiderMusicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: hwt
 * @date: 2020/9/24 23:13
 * @description:
 */
@RestController
@RequestMapping("/music")
@Api(value = "音乐接口", tags = "音乐接口")
public class MusicController {
    @Resource
    private SpiderMusicService spiderMusicService;

    @PostMapping("/list")
    @ApiOperation(value = "音乐搜索",notes = "音乐搜索")
    public Result<List<SpiderMusic>> list(@ApiParam(value = "{\"keyword\":\"关键字\"}") @RequestBody SpiderMusicParam spiderMusicParam){
        List<SpiderMusic> list = spiderMusicService.getList(spiderMusicParam.getKeyword());
        return RetureResult.OK(list);
    }

    @PostMapping("/listPrecise")
    @ApiOperation(value = "音乐精确搜索",notes = "音乐精确搜索")
    public Result<List<SpiderMusic>> listPrecise(@ApiParam(value = "{\"author\":\"作者\",\"musicName\":\"音乐名字\"}") @RequestBody SpiderMusicParam spiderMusicParam){
        List<SpiderMusic> list = spiderMusicService.getPrecis(spiderMusicParam);
        return RetureResult.OK(list);
    }
}
