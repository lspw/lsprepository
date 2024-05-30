package com.lsp.pojo;

import java.io.Serializable;

/*
* 要实现序列化的类必须实现Serializable接口否则会报异常无法序列化
* NotSerializableException
* */

public class Student  implements Serializable {
    private String name;
    private int id;
    private int age;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
