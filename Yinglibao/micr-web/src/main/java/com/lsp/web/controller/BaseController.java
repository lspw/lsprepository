package com.lsp.web.controller;

import com.lsp.ylb.api.service.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

public class BaseController {
    /*声明公共的方法属性等*/

    @Resource
    protected StringRedisTemplate stringRedisTemplate;
    /*平台基本信息*/
    @DubboReference(interfaceClass = PlatBaseInfoService.class,version = "1.0-SNAPSHOT")
    protected PlatBaseInfoService platBaseInfoService;
    /*平台首页产品信息*/
    @DubboReference(interfaceClass = ProductService.class,version = "1.0-SNAPSHOT")
    protected ProductService productService;
    @DubboReference(interfaceClass = InvestService.class,version = "1.0-SNAPSHOT")
    protected InvestService investService;

    @DubboReference(interfaceClass = UserService.class,version = "1.0-SNAPSHOT")
    protected UserService userService;
    @DubboReference(interfaceClass = RechargeService.class,version = "1.0-SNAPSHOT")
    protected RechargeService rechargeService;
}
