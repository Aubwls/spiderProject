package com.hwt.spider.result;

import lombok.Data;

@Data
public class Result<T> {
        //状态码
        private int code;
        //信息
        private String msg;
        //数据
        private Object data;

        public Result(int code, String msg, Object data) {
                this.code = code;
                this.msg = msg;
                this.data = data;
        }

        public Result() {

        }
}
