package com.hwt.spider.controller;

import com.hwt.spider.result.Result;
import com.hwt.spider.result.ReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: hwt
 * @date: 2020/11/11
 * @description:
 */
@RestController
@RequestMapping("/fileUpload")
@Api(value = "文件上传接口", tags = "文件上传接口")
public class FileUploadController {


    @RequestMapping(value= "/upload", method = RequestMethod.POST)
    @ApiOperation(value = "文件上传", notes = "文件上传")
    public Result upload(MultipartFile file){

        return ReturnResult.OK();
    }

}
