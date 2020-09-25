package com.hwt.spider.entity.pojo;

import lombok.Data;

@Data
public class Result<T> {
        //状态码
        private String code;
        //信息
        private String msg;
        //数据
        private Object data;

        public Result(String code, String msg, Object data) {
                this.code = code;
                this.msg = msg;
                this.data = data;
        }

        public Result() {

        }
}
