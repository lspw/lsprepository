package com.lsp.axios.controller;

import com.lsp.axios.pojo.User;
import org.springframework.web.bind.annotation.*;


//@CrossOrigin表示该controller支持跨域请求
@CrossOrigin
@RestController
public class AxiosController {

    @GetMapping("/axios/test")
    public User testAxios(){
        System.out.println("接收到前端的请求");
        User user=new User("lisi",32,"女","西安");

        return user;
    }

    @GetMapping("/axios/testGet")
    public User testAxiosGet(String name,Integer age){
        System.out.println("接收到前端的请求");
        User user=new User(name,age,"女","西安");
        return user;
    }

    @PostMapping("/axios/testPost")
    public User testAxiosPost(String name,Integer age){
        System.out.println("接收到前端的请求，返回参数为：name="+name+"age="+age);
        User user=new User(name,age,"女","西安");
        return user;
    }

    @PostMapping("/axios/testPostJson")
    public User testAxiosPostJson(@RequestBody User user){
        System.out.println("接收到前端的请求，返回参数为："+user);
        User users=new User("lishaoping",23,"男","西安");
        return user;
    }
}
