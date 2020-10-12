package com.hwt.spider.template;

/**
 * @Author hwt
 * @Date:13:46 2020/10/12
 * @Description
 */
public class SendMailTemplate {

    public static String VerficationCode (String accoutNum, String code){
        return "【wtSpider】本次注册验证码为："+code+"(账号为"+accoutNum+")";
    }

}
