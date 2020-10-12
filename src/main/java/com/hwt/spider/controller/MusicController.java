package com.hwt.spider.controller;

import com.hwt.spider.entity.param.SpiderMusicParam;
import com.hwt.spider.result.Result;
import com.hwt.spider.entity.pojo.SpiderMusic;
import com.hwt.spider.result.ReturnResult;
import com.hwt.spider.service.SpiderMusicService;
import com.sun.deploy.net.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ApiOperation(value = "音乐搜索",notes = "音乐搜索")
    public Result<List<SpiderMusic>> list(@RequestBody SpiderMusicParam spiderMusicParam){
        List<SpiderMusic> list = spiderMusicService.getList(spiderMusicParam.getKeyword());
        return ReturnResult.OK(list);
    }

    @RequestMapping(value = "/listPrecise",method = RequestMethod.POST)
    @ApiOperation(value = "音乐精确搜索",notes = "音乐精确搜索")
    public Result<List<SpiderMusic>> listPrecise(@RequestBody SpiderMusicParam spiderMusicParam){
        List<SpiderMusic> list = spiderMusicService.getPrecis(spiderMusicParam);
        return ReturnResult.OK(list);
    }
}
