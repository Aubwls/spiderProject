package com.hwt.spider.service.impl;

import com.hwt.spider.mapper.SpiderMusicMapper;
import com.hwt.spider.service.SpiderMusicService;
import com.hwt.spider.spiderUtils.spiderMusic;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public void list(String keyword) {
        spiderMusicMapper.insertList(spiderMusic.musicList(keyword));
    }

}
