package com.jonm.respond;



public enum ResultEnum {

    SUCCESS("0", "成功"),

    SYSTEM_ERROR("10000","系统异常，稍后再试"),
    UNAUTHORIZED("10401", "签名验证失败"),

    PARAM_ERROR("10001", "参数不合法"),

    DATABASE_ERROR("202", "数据库异常"),

    USER_HAS_EXISTED("20001", "用户名已存在"),
    USER_NOT_FIND("20002", "用户名不存在");


    private String code;
    private String message;

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}