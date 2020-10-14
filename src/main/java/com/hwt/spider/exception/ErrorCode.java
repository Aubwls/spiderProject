package com.hwt.spider.exception;

/**
 * @Author hwt
 * @Date:17:10 2020/9/25
 * @Description
 */
public class ErrorCode {

    public static final String ERROR_SYSTEM = "系统出现问题";

    //爬虫异常
    public static final String MUSIC_AUTHOR_IS_EMPTY = "音乐作者不能为空";

    //用户异常
    public static final String USER_ACCOUT_NUMBER_IS_EMPTY = "账号不能为空";

    public static final String USER_USER_NAME_IS_EMPTY = "账号不能为空";

    public static final String USER_MAIL_IS_EMPTY = "邮箱不能为空";

    public static final String USER_PASS_WORD_IS_EMPTY = "密码不能为空";

    public static final String USER_PASS_WORD_IS_ERROR = "密码错误";

    public static final String USER_IS_NOT_EXIST = "用户不存在";

    public static final String USER_VERFICATION_CODE_IS_EMPTY = "验证码为空";

    public static final String USER_VERFICATION_CODE_IS_ERROR = "验证码错误";

    //发送邮件异常
    public static final String SEND_MAIL_IS_DEFEAT = "发送邮件失败";

}
