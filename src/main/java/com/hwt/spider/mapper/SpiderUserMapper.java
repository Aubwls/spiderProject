package com.hwt.spider.mapper;

import com.hwt.spider.entity.pojo.SpiderUser;

public interface SpiderUserMapper {

    SpiderUser getUserByAccoutNumber(Long accoutNumber);

    void insert(SpiderUser record);

    void deleteById(Long userId);
}