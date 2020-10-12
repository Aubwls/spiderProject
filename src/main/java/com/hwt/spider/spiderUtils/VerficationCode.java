package com.hwt.spider.spiderUtils;

import java.util.Random;

/**
 * @Author hwt
 * @Date:13:54 2020/10/12
 * @Description
 */
public class VerficationCode {
    private static char param [] =  "ABCDEFGHIJKLOMNOPQRSTUVWXYZ1234567890".toCharArray();

    public static String getCode(){
        StringBuffer code = new StringBuffer();
        Random rand = new Random();
        for(int i = 0 ; i < 6 ; i++){
            int randNumber =rand.nextInt(35 - 0 + 1) + 0;
            code.append(param[randNumber]);
        }
        return code.toString();
    }

}
