package com.lsp;

import com.lsp.pojo.Student;
import com.lsp.pojo.User;
import org.junit.Test;

import java.io.*;
import java.util.*;

public class SerializeTest {
    @Test
    //序列化
    public void testSerialize() throws Exception {
        Student student=new Student();
        student.setName("lishaoping");
        student.setAge(23);
        student.setId(2510);

        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("studnets"));
        //序列化对象
        oos.writeObject(student);
        //刷新
        oos.flush();
        //关闭
        oos.close();
    }
    @Test
    //反序列化
    public void ReverseSerializable() throws Exception {
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("studnets"));
        Object object=ois.readObject();
        System.out.println(object);
        ois.close();

    }
    @Test
    //序列化多个对象
    public void testSerializableObjects() throws Exception {
        User user=new User();
        List<User> list=new ArrayList<>();
        list.add(new User("li",12));
        list.add(new User("wang",25));
        list.add(new User("lisi",10));
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("User"));
        //开始序列化
        oos.writeObject(list);
        //刷新
        oos.flush();
        //关闭
        oos.close();
    }
    @Test
    //反序列化多个
    public void ReverseSerializableObjects() throws Exception {
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("User"));
        //Object o = ois.readObject();
        //System.out.println(o instanceof List);
        List<User> userList = (List<User>)ois.readObject();
        System.out.println(userList);
        ois.close();
    }
    @Test
    public  void plus(){
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        queue.offer(7);
        queue.offer(8);
        queue.offer(9);
        int size = queue.size();
        int get=queue.poll();
        int a=size--;
        System.out.println("size="+size);
        System.out.println("size--="+a);

    }
    @Test
    public void s(){
        int a=9;
        int b=a--;
        System.out.println(a);
        System.out.println(b);
    }

}
