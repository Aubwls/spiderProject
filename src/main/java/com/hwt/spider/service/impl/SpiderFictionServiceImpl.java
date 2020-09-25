package com.hwt.spider.service.impl;

import com.hwt.spider.entity.pojo.SpiderFiction;
import com.hwt.spider.mapper.SpiderFictionMapper;
import com.hwt.spider.service.SpiderFictionService;
import com.hwt.spider.spiderUtils.spiderFiction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author hwt
 * @Date:13:49 2020/9/25
 * @Description
 */
@Service
public class SpiderFictionServiceImpl implements SpiderFictionService {

    @Resource
    private SpiderFictionMapper spiderFictionMapper;

    @Override
    public List<SpiderFiction> getList(String keyword) {
        List<SpiderFiction> spiderFictions = null;
        spiderFictions = spiderFictionMapper.selectByParam(keyword);
        if (spiderFictions.size() == 0){
            spiderFictions = spiderFiction.fictionList(keyword);
            if (spiderFictions.size() != 0){
                spiderFictionMapper.insertList(spiderFictions);
            }
        }
        return spiderFictions;
    }
}
