package com.hwt.spider.exception;

import com.hwt.spider.result.Result;
import com.hwt.spider.result.ReturnResult;
import org.springframework.jdbc.core.SqlReturnResultSet;

/**
 * @Author hwt
 * @Date:17:04 2020/9/25
 * @Description
 */
public class BusinessException extends RuntimeException {

    private int httpCode = 300;

    private String code;

    public BusinessException(String code){
        this.code = code;
    }
    public Result toReturnResult(){
        return ReturnResult.ERROR(httpCode,code);
    }
}
