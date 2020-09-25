package com.hwt.spider.mapper;

import com.hwt.spider.entity.pojo.SpiderFiction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpiderFictionMapper {

    void insertList(@Param("spiderFictions") List<SpiderFiction> spiderFictions);

    List<SpiderFiction> selectByParam(@Param("keyword") String keyword);
}