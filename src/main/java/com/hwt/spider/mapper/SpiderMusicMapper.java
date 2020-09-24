package com.hwt.spider.mapper;

import com.hwt.spider.bean.pojo.SpiderMusic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpiderMusicMapper {
    int insertList(@Param("spiderMusics") List<SpiderMusic> spiderMusics);
}