package com.hwt.spider.spiderUtils;

import com.hwt.spider.exception.BusinessException;
import com.hwt.spider.exception.ErrorCode;
import com.hwt.spider.template.SendMailTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author hwt
 * @Date:16:05 2020/10/10
 * @Description 使用shell命令发送邮件
 */
public class sendMail {

    private static void sendMail(String command){
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
                throw new BusinessException(ErrorCode.SEND_MAIL_IS_DEFEAT);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendCode(Long accoutNum, String code, String mail) {
        String command = "echo \"验证码\" | mail -v -s "+ SendMailTemplate.VerficationCode(accoutNum,code) +" "+ mail;
        sendMail(command);
    }
}
