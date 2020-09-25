package com.hwt.spider.service;

import com.hwt.spider.entity.pojo.SpiderFiction;
import com.hwt.spider.entity.pojo.SpiderMusic;

import java.util.List;

/**
 * @Author hwt
 * @Date:13:47 2020/9/25
 * @Description
 */
public interface SpiderFictionService {

    List<SpiderFiction> getList(String keyword);
}
