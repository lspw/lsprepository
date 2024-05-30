package com.lsp.test;

import com.alibaba.fastjson.JSONObject;
import com.lsp.pojo.School;
import com.lsp.pojo.Student;
import org.junit.Test;

import java.util.Set;

public class TestComplexJsonConversion {
    @Test
    public void testComplexJsonConversion(){
        Student student=new Student();
        School school=new School();

        student.setName("wang");
        student.setId(01);
        student.setAge(21);
        student.setBirth("0918");
        student.setSchool(school);

        school.setName("西安文理学院");
        school.setAddress("西安市太白南路");
        school.setPeoples(11000);

        String jsonString = JSONObject.toJSONString(student);
        System.out.println("jsonString = " + jsonString);
        //{"age":21,"birth":"0918","id":1,"name":"wang","school":{"address":"西安市太白南路","name":"西安文理学院","peoples":11000}}
    }
    @Test
    public void jsonToObject(){
        String json="{\"age\":21,\"birth\":\"0918\",\"id\":1,\"name\":\"wang\",\"school\":{\"address\":\"西安市太白南路\",\"name\":\"西安文理学院\",\"peoples\":11000}}";

        Student student = JSONObject.parseObject(json, Student.class);
        System.out.println("student = " + student);
    }
    @Test
    //去复杂json中的一个值
    public void getComplexJsonOneKey(){
        String json="{\"age\":21,\"birth\":\"0918\",\"id\":1,\"name\":\"wang\",\"school\":{\"address\":\"西安市太白南路\",\"name\":\"西安文理学院\",\"peoples\":11000}}";
        //将json转化为一个Map
        JSONObject jsonObject = JSONObject.parseObject(json);
        String string = jsonObject.getJSONObject("school").getString("address");
        System.out.println("string = " + string);
    }

}
