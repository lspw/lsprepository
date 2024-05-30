package com.lsp;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class JwtTest {

    //创建Jwt
    @Test
    public void createJwt(){
     String key="ce94f21d1e8e4c3197bee2ec7596e52b";
     //创建SeceretKey将可以转换为一个可用的对象
     SecretKey secretkey= Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    //创建Jwt,使用Jwt类
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("name","张三");
        hashMap.put("id",1222);
        hashMap.put("address","西安");

        String compact = Jwts.builder().signWith(secretkey, SignatureAlgorithm.HS256)
                .setExpiration(DateUtils.addMinutes(new Date(), 10))//过期时间
                .setIssuedAt(new Date())//签发时间
                .setId(UUID.randomUUID().toString())
                .addClaims(hashMap).compact();
        System.out.println(compact);
    }
    @Test
    public void readTest(){
        String Jwt="eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE3MTUyMzkyNTUsImlhdCI6MTcxNTIzODY1NSwianRpIjoiMTdjMmQ4YzYtMjVjOS00ZDY0LTk0ZjEtMTBmM2M5YzllM2U1IiwiYWRkcmVzcyI6Iuilv-WuiSIsIm5hbWUiOiLlvKDkuIkiLCJpZCI6MTIyMn0.Ll0LzzMq_x3Dld8fVmals04VX-GEINbD91U3okH5NL4";
        String key="ce94f21d1e8e4c3197bee2ec7596e52b";
        //创建SeceretKey将可以转换为一个可用的对象
        SecretKey secretkey= Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
        //解析Jwt,没有异常解析成功
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretkey).build()
                .parseClaimsJws(Jwt);

        //读取数据
        Claims body=claims.getBody();
        Integer id = body.get("id", Integer.class);
        System.out.println("id="+id);
        Object add = body.get("address");
        System.out.println("address="+add);

        Object name = body.get("name");
        if (name!=null){
            String str=(String)name;
            System.out.println("str="+str);
        }
        String jwtid = body.getId();
        System.out.println("jwtid="+jwtid);


    }


}
