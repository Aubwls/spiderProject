package com.hwt.spider.mapper;

import com.hwt.spider.entity.pojo.SpiderMusic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpiderMusicMapper {
    void insertList(@Param("spiderMusics") List<SpiderMusic> spiderMusics);
}