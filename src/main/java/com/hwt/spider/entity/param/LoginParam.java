package com.hwt.spider.entity.param;

import lombok.Data;

/**
 * @Author hwt
 * @Date:11:21 2020/9/28
 * @Description
 */
@Data
public class LoginParam {

    private String username;

    private String password;

    private String mail;

    private String code;
}
