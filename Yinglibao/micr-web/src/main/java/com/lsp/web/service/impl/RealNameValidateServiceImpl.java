package com.lsp.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lsp.commons.utils.HttpClientUtils;
import com.lsp.web.config.JdwxRealNameConfig;
import com.lsp.web.service.RealNameValidateService;
import com.lsp.ylb.api.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.Param;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class RealNameValidateServiceImpl implements RealNameValidateService {
    @Resource
    private JdwxRealNameConfig jdwxRealNameConfig;
    @Resource
    private UserService userService;

    @Override
    public boolean realNameValidate(String name, String idCard, String phone,String code) {
        Map<String,String> params=new HashMap<>();
        params.put("cardNo",idCard);
        params.put("realName",name);
        params.put("code",code);
        params.put("appkey",jdwxRealNameConfig.getAppkey());
        boolean realname=false;
        try {
            //String response=  HttpClientUtils.doGet(jdwxRealNameConfig.getUrl(),params);
            String response="{\n" +
                    "    \"code\": \"10000\",\n" +
                    "    \"charge\": false,\n" +
                    "    \"remain\": 1305,\n" +
                    "    \"msg\": \"查询成功\",\n" +
                    "    \"result\": {\n" +
                    "        \"error_code\": 0,\n" +
                    "        \"reason\": \"成功\",\n" +
                    "        \"result\": {\n" +
                    "            \"realname\": \""+name+"\",\n" +
                    "            \"idcard\": \"350721197702134399\",\n" +
                    "            \"isok\": true\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";

                if (StringUtils.isNotBlank(response)){
                    //fastJson解析返回的json
                    JSONObject responseObject = JSONObject.parseObject(response);
                        if ("10000".equalsIgnoreCase(responseObject.getString("code"))){
                            //解析resule
                            realname=responseObject.getJSONObject("result")
                                    .getJSONObject("result")
                                    .getBooleanValue("isok");
                            //数据返回成功而，处理数据库
                            boolean resultRealName=userService.modifyRealName(name,idCard,phone);

                            realname=resultRealName;
                        }
                }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return realname;
    }
}
