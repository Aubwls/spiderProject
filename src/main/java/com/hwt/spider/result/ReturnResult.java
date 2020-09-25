package com.hwt.spider.result;

public class ReturnResult {
    private static final int HttpSuccess = 200;
    private static final int HttpError = 300;

    public static Result ERROR(){
        return new Result<Object>(HttpError,"操作失败，请联系管理员！",null);
    }
    public static Result ERROR(int HttpCode,String code ){
        return new Result<Object>(HttpCode,code,null);
    }
    public static Result ERROR(String code ){
        return new Result<Object>(HttpError,code,null);
    }

    public static Result OK(){
        return new Result<Object>(HttpSuccess,"操作成功",null);
    }

    public static Result OK(Object o){
        return new Result<Object>(HttpSuccess,"操作成功",o);
    }

}
