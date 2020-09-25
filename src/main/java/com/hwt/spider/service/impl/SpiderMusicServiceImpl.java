package com.hwt.spider.service.impl;

import com.hwt.spider.entity.pojo.SpiderMusic;
import com.hwt.spider.mapper.SpiderMusicMapper;
import com.hwt.spider.service.SpiderMusicService;
import com.hwt.spider.spiderUtils.spiderMusic;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: hwt
 * @date: 2020/9/24 22:28
 * @description:
 */
@Service
public class SpiderMusicServiceImpl implements SpiderMusicService {
    @Resource
    private SpiderMusicMapper spiderMusicMapper;

    @Override
    public List<SpiderMusic> list(String keyword) {
        List<SpiderMusic> spiderMusics = null;
        spiderMusics = spiderMusicMapper.selectByParam(keyword);
        if (spiderMusics.size() == 0){
            spiderMusics = spiderMusic.musicList(keyword);
            spiderMusicMapper.insertList(spiderMusics);
        }
        return spiderMusics;
    }

}
