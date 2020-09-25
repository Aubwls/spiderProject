package com.hwt.spider.exception;

import com.hwt.spider.result.Result;
import com.hwt.spider.result.ReturnResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author hwt
 * @Date:17:13 2020/9/25
 * @Description
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result globalErrorHandler(HttpServletRequest request, Exception e){
        if (e instanceof BusinessException) {
            return ((BusinessException) e).toReturnResult();
        } else {
            e.printStackTrace();
            return ReturnResult.ERROR(ErrorCode.ERROR_SYSTEM);
        }
    }
}
