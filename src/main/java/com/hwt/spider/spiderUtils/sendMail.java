package com.hwt.spider.spiderUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author hwt
 * @Date:16:05 2020/10/10
 * @Description 使用shell命令发送邮件
 */
public class sendMail {

    public static void sendMail(String command){
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            process.getInputStream()));
            String data = "";
            while((data = reader.readLine()) != null) {
                System.out.println(data);
            }

            int exitValue = process.waitFor();

            if(exitValue != 0) {
                System.out.println("error");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String command = "ls -l";
        sendMail(command);
    }
}
