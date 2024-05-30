package com.lsp.web.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lsp.commons.enums.ResponseCode;
import com.lsp.commons.utils.JWTUtils;
import com.lsp.web.view.ResponseResult;
import com.sun.corba.se.pept.transport.OutboundConnectionCache;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.HeaderTokenizer;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class TokenInterceptor implements HandlerInterceptor {

    private String secret="";

    public TokenInterceptor(String secret) {
        this.secret = secret;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是OPTIONS,放行，OPTIONS中没有任何参数
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())){
            return true;
        }
        boolean isRequestSend=false;
        try {
            //获取token进行验证
            String uid=request.getHeader("uid");//请求头传过来的uid
            String headerToken=request.getHeader("Authorization");
            if (StringUtils.isNotBlank(headerToken)){
                //Bearer
                String jwt=headerToken.substring(6);
                //读jwt
                JWTUtils jwtUtils=new JWTUtils(secret);
                Claims claims = jwtUtils.readJwt(jwt);
                //获取jwt中的数据，uid
                Integer jwtUid = claims.get("uid", Integer.class);//jwt中解析的uid

                if (uid.equals(String.valueOf(jwtUid))){
                    //token和发送请求的人时一个
                    isRequestSend=true;

                }
            }


        }catch (Exception e){
            isRequestSend=false;
            e.printStackTrace();
        }finally {
            //token验证没有通过要给前端vue错误提示
            //给前端 返回一个json数据
            if (isRequestSend==false){
                ResponseResult result=ResponseResult.fail();
                result.setResponseCode(ResponseCode.TOKEN_INVALID);
                //使用HttServletResponse输出
                String jsonString = JSONObject.toJSONString(result);
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out=response.getWriter();
                out.println(jsonString);
                out.flush();
                out.close();
            }
        }
        return isRequestSend;
    }
}
