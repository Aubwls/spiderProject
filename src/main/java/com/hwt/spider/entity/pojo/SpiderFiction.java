package com.hwt.spider.entity.pojo;

import lombok.Data;


@Data
public class SpiderFiction extends AbstractPojo{
    private String fictionName;

    private String author;

    private String url;

}