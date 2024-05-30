package com.lsp.web.setting;

import com.lsp.web.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        TokenInterceptor tokenInterceptor=new TokenInterceptor(jwtSecret);

        /*List<String> addPath=new ArrayList<>();
        addPath.add("/v1/user/realnamevaildate");*/
        String [] addPath = {"/v1/user/realnamevaildate"};
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns(addPath);

    }

    /*处理跨域*/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("===========addCorsMappings===========");

        //addMapping 处理的请求地址， 拦截这些地址，使用跨域处理逻辑
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:8080")  //可跨域的域名，可以为 *
                //支持跨域请求的，http方式
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                //支持跨域的请求头， 在请求头包含哪些数据时，可以支持跨域功能
                .allowedHeaders("*");
    }
}
