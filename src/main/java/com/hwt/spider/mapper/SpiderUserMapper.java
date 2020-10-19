package com.hwt.spider.mapper;

import com.hwt.spider.entity.pojo.SpiderUser;
import org.apache.ibatis.annotations.Param;

public interface SpiderUserMapper {

    SpiderUser getUserByMail(String accoutNumber);

    void insert(SpiderUser record);

    void deleteById(Long userId);

    SpiderUser selectByPrimaryKey(@Param("id") Long id);
}