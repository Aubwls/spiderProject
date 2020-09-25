package com.hwt.spider.service;

import com.hwt.spider.entity.param.SpiderMusicParam;
import com.hwt.spider.entity.pojo.SpiderMusic;

import java.util.List;

/**
 * @author: hwt
 * @date: 2020/9/24 21:23
 * @description:
 */
public interface SpiderMusicService {

    List<SpiderMusic> getList(String keyword);

    List<SpiderMusic> getPrecis(SpiderMusicParam spiderMusicParam);
}
