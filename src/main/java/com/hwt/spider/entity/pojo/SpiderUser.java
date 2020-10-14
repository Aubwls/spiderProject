package com.hwt.spider.entity.pojo;

import lombok.Data;

@Data
public class SpiderUser extends AbstractPojo {

    private Long accoutNumber;

    private String mail;

    private String userName;

    private String passWord;

}