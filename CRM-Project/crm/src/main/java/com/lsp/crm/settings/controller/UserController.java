package com.lsp.crm.settings.controller;

import com.lsp.crm.commons.contants.Contants;
import com.lsp.crm.commons.pojo.ReturnObject;
import com.lsp.crm.commons.utils.DateFormatUtils;
import com.lsp.crm.settings.pojo.User;
import com.lsp.crm.settings.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/settings/qx/user/toLogin")
    public String toLogin(){
        return "settings/qx/user/login";
    }
    @RequestMapping("/settings/qx/user/login")
    public @ResponseBody/*返回json*/ Object login(String loginAct, String loginPwd, String isRemPwd,
                                                  HttpServletRequest request, HttpSession session, HttpServletResponse response){
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        //调用Service层的方法查询用户
        User user= userService.queryUserByLoginActAndPwd(map);
        ReturnObject returnObject=new ReturnObject();
        if (user==null) {
            //登录失败，
            returnObject.setCode("0");
            returnObject.setMessage("账号或密码错误或不存在");
        }else{//进一步判断登录是否能成功
            //判断账号书否到期
            String expireTime = user.getExpireTime();
            String nowTimeStr = DateFormatUtils.formatUtil(new Date());
            if(expireTime.compareTo(nowTimeStr)>0){
                //登录失败，账号已过期
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账号已过期");

                //判断账号书否被锁定
            }else if(Contants.RETURN_OBJECT_CODE_FAIL.equals(user.getLockState())){
                //登录失败，账号已被锁定
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账号已被锁定");

                //判断ip是否受限
            }else if(!user.getAllowIps().contains(request.getLocalAddr())){
                //登录失败，ip受限
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("ip受限");
            }else {
                //登陆成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                session.setAttribute(Contants.SESSION_USER,user);
                //实现记住密码
                if("true".equals(isRemPwd)){
                    Cookie loginAct1 = new Cookie("loginAct",user.getLoginAct());
                    loginAct1.setMaxAge(10*24*60*60);
                    response.addCookie(loginAct1);
                    Cookie loginPwd1 = new Cookie("loginPwd", user.getLoginPwd());
                    loginPwd1.setMaxAge(10*24*60*60);
                    response.addCookie(loginPwd1);
                }else{
                    Cookie loginAct1 = new Cookie("loginAct","1");
                    loginAct1.setMaxAge(0);
                    response.addCookie(loginAct1);
                    Cookie loginPwd1 = new Cookie("loginPwd", "1");
                    loginPwd1.setMaxAge(0);
                    response.addCookie(loginPwd1);
                }
            }
        }
        return returnObject;
    }
}


