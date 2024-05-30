package com.lsp;

import org.junit.Test;

public class StringTest {
    @Test
    public void test01(){
        //StringBuilder线程不安全的
        //StringBuilder stringBuilder=new StringBuilder("指定字符串的初始长度，超出初始长度会自动扩容，但
        // 是，效率会受影响，所以，应该定义时预估长度坚守扩容次数，来提高效率");
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(1111);
        stringBuilder.append("wang");
        stringBuilder.append("lishaoping");
        stringBuilder.append(true);
        System.out.println("stringBuilder = " + stringBuilder);
    }
    @Test
    public void test02(){
        //StringBuffer线程安全的
        //StringBuffer stringBuffer=new StringBuffer();("指定字符串的初始长度，超出初始长度会自动扩容，但
        // 是，效率会受影响，所以，应该定义时预估长度坚守扩容次数，来提高效率");
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("wang");
        stringBuffer.append("li");
        stringBuffer.append(918);
        stringBuffer.append(false);
        System.out.println("stringBuffer = " + stringBuffer);
    }
}
