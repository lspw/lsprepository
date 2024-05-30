package com.lsp.web.service;

public interface SmsService {
    public boolean sendSms(String phone);

    /**
     *
     * @param phone 手机号
     * @param code  提交的验证码
     * @return
     */
    public boolean checkSmsCode(String phone,String code);
}
