package com.lsp.web.controller;

import com.lsp.commons.constant.RedisKey;
import com.lsp.commons.enums.ResponseCode;
import com.lsp.commons.utils.CommonUtil;
import com.lsp.web.service.SmsService;
import com.lsp.web.view.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "短息验证码业务")
@RestController
@RequestMapping("/v1/sms")
public class SmsController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private SmsService service;
    @ApiOperation(value = "发送验证码",notes="")
    @GetMapping("/code/register")
    public ResponseResult sendCodeRegister(@RequestParam("phone") String phone){
        ResponseResult result=ResponseResult.fail();
        if (CommonUtil.checkPhone(phone)){
            //判断redis中是否有这个手机的验证码
            String key= RedisKey.KEY_SMS_CODE_REG+phone;
            if(stringRedisTemplate.hasKey(key)){
                result=ResponseResult.success();
                result.setResponseCode(ResponseCode.SMS_CODE_CAN_USE);
            }
            boolean isSuccess = service.sendSms(phone);
            if (isSuccess){
                result=ResponseResult.success();
            }else{
                result.setResponseCode(ResponseCode.REQUEST_PARAM_TYPE_ERR);
            }
        }
        return result;
    }
}
