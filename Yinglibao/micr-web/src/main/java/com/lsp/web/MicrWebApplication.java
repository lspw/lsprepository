package com.lsp.web;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.lsp.commons.utils.JWTUtils;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//启动swagger 和 ui
@EnableSwaggerBootstrapUI
@EnableSwagger2
//启用dubbo服务
@EnableDubbo
@SpringBootApplication
public class MicrWebApplication {

    @Value("${jwt.secret}")
    private  String secertKey;

    //创建jwtutil
    @Bean
    public JWTUtils jwtUtils(){
        JWTUtils jwtUtils=new JWTUtils(secertKey);
        return jwtUtils;
    }

    public static void main(String[] args) {
        SpringApplication.run(MicrWebApplication.class, args);
    }

}
