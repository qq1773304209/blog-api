package com.jonm.respond;

import lombok.Data;

@Data
public class ErrorResult {
    /**
     * @Fields code : 返回状态码
     */
    private String code;

    /**
     * @Fields msg : 返回消息描述
     */
    private String msg;

    /**
     * @Fields exception : 返回异常名
     */
    private String exception;

    public ErrorResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ErrorResult() { }



    public ErrorResult(String code, String msg, String exception) {
        this.code = code;
        this.msg = msg;
        this.exception = exception;
    }

}
