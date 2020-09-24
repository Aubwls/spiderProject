package com.hwt.spider.result;

import com.hwt.spider.bean.pojo.Result;

public class RetureResult {
    private static final String HttpSuccess = "200";
    private static final String HttpDefeat = "300";

    public static Result ERROR(){
        return new Result<Object>(HttpDefeat,"操作失败，请联系管理员！",null);
    }

    public static Result OK(){
        return new Result<Object>(HttpSuccess,"操作成功",null);
    }

    public static Result OK(Object o){
        return new Result<Object>(HttpSuccess,"操作成功",o);
    }

}
