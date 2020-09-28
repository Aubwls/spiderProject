package com.hwt.spider.entity.pojo;

import lombok.Data;

@Data
public class SpiderUser extends AbstractPojo {

    private Integer accoutNumber;

    private String userName;

    private String passWord;

}