package com.hwt.spider.service.impl;

import com.hwt.spider.service.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author: hwt
 * @date: 2020/11/11
 * @description:
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {


    @Override
    public void upload(MultipartFile file) {

    }

    public static void main(String[] args) {
        List<Integer> lists = Arrays.asList(1, 3, 2, 8, 11, 4);
        Integer sumSalary2 = lists.stream().reduce(0, (sum, p) -> sum+=p);
        System.out.println(lists);
        System.out.println(sumSalary2);
    }
}
