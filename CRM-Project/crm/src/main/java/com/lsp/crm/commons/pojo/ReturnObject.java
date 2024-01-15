package com.lsp.crm.commons.pojo;

public class ReturnObject {
    private String code;//处理成功或失败的标记；成功1，失败0
    private String message;//提示信息
    private Object retData;

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

    public Object getRetData() {
        return retData;
    }

    public void setRetData(Object retData) {
        this.retData = retData;
    }
}
