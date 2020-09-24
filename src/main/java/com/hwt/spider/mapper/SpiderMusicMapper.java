package com.hwt.spider.mapper;

import com.hwt.spider.bean.pojo.SpiderMusic;

public interface SpiderMusicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpiderMusic record);

    int insertSelective(SpiderMusic record);

    SpiderMusic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpiderMusic record);

    int updateByPrimaryKey(SpiderMusic record);
}