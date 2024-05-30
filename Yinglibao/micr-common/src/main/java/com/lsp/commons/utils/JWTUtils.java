package com.lsp.commons.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JWTUtils {

    private String  selfKey;

    public JWTUtils(String selfKey) {
        this.selfKey = selfKey;
    }

    public String createToken(Map<String,Object> data, Integer minute) throws Exception{

        /*String key="ce94f21d1e8e4c3197bee2ec7596e52b";*/
        //创建SeceretKey将可以转换为一个可用的对象
        SecretKey secretkey= Keys.hmacShaKeyFor(selfKey.getBytes(StandardCharsets.UTF_8));
        //创建Jwt,使用Jwt类
       /* HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("name","张三");
        hashMap.put("id",1222);
        hashMap.put("address","西安");*/

        String jwt = Jwts.builder().signWith(secretkey, SignatureAlgorithm.HS256)
                .setExpiration(DateUtils.addMinutes(new Date(), minute))//过期时间
                .setIssuedAt(new Date())//签发时间
                .setId(UUID.randomUUID().toString().replaceAll("-",""))
                .addClaims(data).compact();
        return jwt;
    }
    //读取jwt
    public Claims readJwt(String jwt) throws Exception {
        SecretKey secretKey=Keys.hmacShaKeyFor(selfKey.getBytes(StandardCharsets.UTF_8));
        Claims body= Jwts.parserBuilder().setSigningKey(secretKey)
                .build().parseClaimsJws(jwt).getBody();
        return body;

    }


}

