package com.hwt.spider.controller;

import com.hwt.spider.entity.param.SpiderMusicParam;
import com.hwt.spider.result.Result;
import com.hwt.spider.entity.pojo.SpiderMusic;
import com.hwt.spider.result.ReturnResult;
import com.hwt.spider.service.SpiderMusicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
public class MusicController {
    @Resource
    private SpiderMusicService spiderMusicService;

    @RequestMapping("/list")
    @ApiOperation(value = "音乐搜索",notes = "音乐搜索")
    public Result<List<SpiderMusic>> list(@RequestParam String keyword){
        List<SpiderMusic> list = spiderMusicService.getList(keyword);
        return ReturnResult.OK(list);
    }

    @RequestMapping("/listPrecise")
    @ApiOperation(value = "音乐精确搜索",notes = "音乐精确搜索")
    public Result<List<SpiderMusic>> listPrecise(@RequestBody SpiderMusicParam spiderMusicParam){
        System.out.println(1/0);
        List<SpiderMusic> list = spiderMusicService.getPrecis(spiderMusicParam);
        return ReturnResult.OK(list);
    }
}
