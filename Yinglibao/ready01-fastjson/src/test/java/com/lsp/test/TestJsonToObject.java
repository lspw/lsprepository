package com.lsp.test;

import com.alibaba.fastjson.JSONObject;
import com.lsp.pojo.Student;
import org.junit.Test;

public class TestJsonToObject {

    @Test
    public void testJson(){
        Student student=new Student();
        student.setAge(21);
        student.setBirth("0918");
        student.setId(01);
        student.setName("wang");
        //Student-->json
        String json= JSONObject.toJSONString(student);
        System.out.println("json = " + json);
        //{"age":21,"birth":"0918","id":1,"name":"wang"}
    }
    @Test
    public void testJsonToStudent(){
        String json="{\"age\":21,\"birth\":\"0918\",\"id\":1,\"name\":\"wang\"}";
        //json-->Student
        Student student = JSONObject.parseObject(json, Student.class);
        System.out.println("student = " + student);
    }
    @Test
    //测试取到json中的一个值
    public void testGetOneKeyFromJson(){
        String json="{\"age\":21,\"birth\":\"0918\",\"id\":1,\"name\":\"wang\"}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        //将json转换为了一个Map集合（jsonObject是一个map集合）
        String age = jsonObject.getString("age");
        String name = jsonObject.getString("name");
        System.out.println("age = " + age);
        System.out.println("name = " + name);

    }
}
