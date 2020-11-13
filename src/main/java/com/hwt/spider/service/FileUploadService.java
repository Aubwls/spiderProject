package com.hwt.spider.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: hwt
 * @date: 2020/11/11
 * @description:
 */
public interface FileUploadService {

    void upload(MultipartFile file);

}
