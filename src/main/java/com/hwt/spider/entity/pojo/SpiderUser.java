package com.hwt.spider.entity.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SpiderUser extends AbstractPojo implements Serializable {

    private Long accoutNumber;

    private String mail;

    private String userName;

    private String passWord;

}