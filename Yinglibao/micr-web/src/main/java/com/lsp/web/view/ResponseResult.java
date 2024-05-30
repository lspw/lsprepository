package com.lsp.web.view;

import com.lsp.commons.enums.ResponseCode;

import java.util.List;

public class ResponseResult {
    /*表示返回的应答结果 值为1000表示成功*/
    private int code;
    /*访问token*/
    private String accessToken;
   /*根据应答结果返回的提示信息*/
    private String msg;
    /*表示返回的数据*/
    private Object data;
    /**/
    private List list;
    private PageInfo pageInfo;


    /*表示请求成功*/
    public static ResponseResult success(){
        ResponseResult result=new ResponseResult();
        /*result.setCode(1000);
        result.setMsg("请求成功");*/
        result.setResponseCode(ResponseCode.SUCCESS);
        return result;
    }
    /*表示请求失败*/
    public static ResponseResult fail(){
        ResponseResult result=new ResponseResult();
        /*result.setCode(0);
        result.setMsg("系统忙，请稍后。。。");*/
        result.setResponseCode(ResponseCode.UNKNOWN);
        return result;
    }

    public void setResponseCode(ResponseCode responseCode){
        this.code=responseCode.getCode();
        this.msg=responseCode.getText();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
