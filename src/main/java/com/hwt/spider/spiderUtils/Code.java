package com.hwt.spider.spiderUtils;

import java.util.Random;

/**
 * @Author hwt
 * @Date:13:54 2020/10/12
 * @Description
 */
public class Code {
    private static final char param [] =  "ABCDEFGHIJKLOMNOPQRSTUVWXYZ1234567890".toCharArray();

    private static final char numParam [] =  "1234567890".toCharArray();

    public static String getVerficationCode(){
        StringBuffer code = new StringBuffer();
        Random rand = new Random();
        for(int i = 0 ; i < 6 ; i++){
            code.append(param[rand.nextInt(35 - 0 + 1) + 0]);
        }
        return code.toString();
    }

    public static String getAccoutNum(){
        StringBuffer code = new StringBuffer();
        Random rand = new Random();
        code.append(numParam[rand.nextInt(9 - 0 + 1) + 0]);
        for(int i = 1 ; i < 10 ; i++){
            code.append(numParam[rand.nextInt(10 - 0 + 1) + 0]);
        }
        return code.toString();
    }

}
