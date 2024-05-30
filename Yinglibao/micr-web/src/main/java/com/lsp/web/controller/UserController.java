package com.lsp.web.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.lsp.commons.enums.ResponseCode;
import com.lsp.commons.utils.CommonUtil;
import com.lsp.commons.utils.JWTUtils;
import com.lsp.web.service.RealNameValidateService;
import com.lsp.web.service.SmsService;
import com.lsp.web.view.ResponseResult;
import com.lsp.web.vo.RealNameVo;
import com.lsp.ylb.api.model.UserAccount;
import com.lsp.ylb.api.pojo.User;
import com.lsp.ylb.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import javax.annotation.Resource;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@Api(tags = "验证手机号是否存在")
@RestController
@RequestMapping("/v1/user")
public class UserController  extends BaseController {
    @Resource
    private UserService userService;
    @Resource
    private SmsService smsService;
    @Resource
    private RealNameValidateService realNameValidateService;
    @Resource
    private JWTUtils jwtUtils;

    /**
     * @param phone 手机号
     * @param pword 密码
     * @param code  前端提交的验证码
     * @return
     */

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public ResponseResult phoneNumRegister(@RequestParam String phone,
                                           @RequestParam String pword,
                                           @RequestParam String code) {
        ResponseResult result = ResponseResult.fail();
        /*判断手机号格式*/
        if (CommonUtil.checkPhone(phone)) {
            /*手机号格式正确*/
            //判断密码格式
            if (pword != null && pword.length() == 32) {//经过md5加密后是一个32位字符串
                //密码格式正确
                //判定验证码是否存在
                if (smsService.checkSmsCode(phone, code)) {
                    //验证码有效可以注册
                    int userRegister = userService.userRegister(phone, pword);
                    if (userRegister == 1) {
                        //注册成功
                        result = ResponseResult.success();
                    } else if (userRegister == 2) {
                        //手机号注册过了
                        result.setResponseCode(ResponseCode.PHONE_EXIST);
                    } else {
                        result.setResponseCode(ResponseCode.REQUESRT_PARAM_ERR);
                    }
                } else {
                    //验证码无效
                    result.setResponseCode(ResponseCode.SMS_CODE_INVALID);
                }
            } else {
                //密码格式不正确
                result.setResponseCode(ResponseCode.REQUESRT_PARAM_ERR);
            }
        } else {
            /*手机号格式不正确*/
            result.setResponseCode(ResponseCode.PHONE_FORMAT_ERROR);
        }

        return result;
    }


    @ApiOperation(value = "验证手机号是否注册过", notes = "在注册过程中判断手机号是否可以注册")
    @ApiImplicitParam(name = "phone", value = "手机号")
    @GetMapping("/phone/exist")
    public ResponseResult phoneExist(String phone) {
        ResponseResult result = new ResponseResult();
        result.setResponseCode(ResponseCode.PHONE_EXIST);
        if (CommonUtil.checkPhone(phone)) {
            //手机号格式正确
            User user = userService.queryUserByPhone(phone);
            //手机号不存在，可以注册
            if (user == null) {
                result = ResponseResult.success();
            }
        } else {
            result.setResponseCode(ResponseCode.PHONE_FORMAT_ERROR);
        }
        return result;
    }

    @ApiOperation(value = "用户登录，获取访问token")
    @PostMapping("/login")
    public ResponseResult login(@RequestParam String phone,
                                @RequestParam String pword,
                                @RequestParam String code) throws Exception {
        ResponseResult result = ResponseResult.fail(); //默认失败
        //数据验证
        if (CommonUtil.checkPhone(phone) && (pword.length() == 32 && pword != null)) {
            //参数正确,提交验证码
            if (smsService.checkSmsCode(phone, code)) {
                //验证码正确，到数据库中对比数据
                User user = userService.queryLoginUser(phone, pword);
                if (user != null) {
                    //user存在说明，登陆成功，生成token
                    Map<String, Object> data = new HashMap<>();
                    data.put("uid", user.getId());
                    String token = jwtUtils.createToken(data, 120);

                    result = ResponseResult.success();
                    result.setAccessToken(token);

                    Map<String, Object> userInfo = new HashMap<>();
                    userInfo.put("uid", user.getId());
                    userInfo.put("phone", user.getPhone());
                    userInfo.put("name", user.getName());
                    result.setData(userInfo);
                } else {
                    result.setResponseCode(ResponseCode.USER_PHONE_PASSWORD_INVALID);
                }

            } else {
                result.setResponseCode(ResponseCode.SMS_CODE_INVALID);
            }

        } else {
            //数据有问题
            result.setResponseCode(ResponseCode.REQUESRT_PARAM_ERR);

        }
        return result;
    }

    @ApiOperation(value = "实名认证")
    @PostMapping("/realnamevaildate")
    public ResponseResult realNameValidate(@RequestBody RealNameVo realNameVo) {
        //默认失败
        ResponseResult result = ResponseResult.fail();
        result.setResponseCode(ResponseCode.REQUESRT_PARAM_ERR);

        //参数格式验证
        if (CommonUtil.checkPhone(realNameVo.getPhone())) {
            if (StringUtils.isNotBlank(realNameVo.getName())
                    && StringUtils.isNotBlank(realNameVo.getIdCard())
                    && StringUtils.isNotBlank(realNameVo.getCode())) {

                boolean realNameValidate = realNameValidateService.realNameValidate(realNameVo.getName()
                        , realNameVo.getIdCard()
                        , realNameVo.getPhone()
                        , realNameVo.getCode());
                if (realNameValidate) {
                    result = ResponseResult.success();
                } else {
                    result.setResponseCode(ResponseCode.REALNAME_FAIL);
                }
            }
        }
        return result;
    }

    @ApiOperation(value = "用户中心")
    @GetMapping("/usercenter")
    public ResponseResult userCenter(@RequestHeader(value = "uid",required = false) Integer uid) {
        ResponseResult result = ResponseResult.fail();
        if (uid != null && uid > 0) {

            UserAccount userAccount = userService.queryUserAccount(uid);

            //封装数据只传回需要的数据
           if (userAccount!=null){
               result=ResponseResult.success();
               Map<String,Object> userData=new HashMap<>();
               userData.put("name",userAccount.getName());
               userData.put("phone",userAccount.getPhone());
               userData.put("money",userAccount.getAvailableMoney());
               userData.put("headerUrl",userAccount.getHeaderImage());

               if (userAccount.getLastLoginTime()!=null){


                   userData.put("logintime", DateFormatUtils.format(userAccount.getLastLoginTime(),"yyyy-MM-dd HH:mm:ss"));
               }else{
                   userData.put("logintime","-");
               }
               result.setData(userData);
           }

        }
        return result;
    }
}