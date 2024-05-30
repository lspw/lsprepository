package com.lsp.commons.enums;

public enum ResponseCode {
    UNKNOWN(0,"稍后请重试"),
    SUCCESS(1000,"请求成功"),
    REQUESRT_PARAM_ERR(1001,"请求参数有误"),
    REQUEST_PARAM_TYPE_ERR(1002,"请求参数的类型有误"),
   PRODUCT_OFFLINE(1003,"产品已下线"),
   PHONE_FORMAT_ERROR(1004,"手机号有误"),
   PHONE_EXIST(1005,"手机号已被注册"),
   SMS_CODE_CAN_USE(1006,"验证码已发送"),
   SMS_CODE_INVALID(1007,"验证码无效"),
    USER_PHONE_PASSWORD_INVALID(1008,"手机号或密码无效"),
    REALNAME_FAIL(1009,"实名认证失败"),



    TOKEN_INVALID(3000,"TOKEN失效")
    ;


    private int code;
    private String text;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * 应答码
     * 0表示默认
     * 1000-2000是请求参数有误，逻辑有问题
     * 2000-3000是服务器请求有问题
     * 3000-4000是访问dubbo服务时出现问题
     */


    ResponseCode(int code, String text) {
        this.code = code;
        this.text = text;
    }


}
