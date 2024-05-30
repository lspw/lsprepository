package com.lsp.pojo.likou;

import com.lsp.pojo.Front.ListNode;
import org.junit.Test;

import java.util.Stack;

public class suanfa {

    @Test
    public boolean isValid(String s) {
        //1.特判
        if(s.isEmpty()) return true;
        //2.创建辅助栈
        Stack<Character> stack = new Stack<>();
        //3.遍历
        for(char c : s.toCharArray()){
            if(c == '('){
                stack.push(')');
            }else if(c == '['){
                stack.push(']');
            }else if(c == '{'){
                stack.push('}');
            }else if(stack.isEmpty() || c != stack.pop()){
                return false;
            }
        }
        //4.返回
        return stack.isEmpty();
    }
    @Test
    public boolean isPalindrome(ListNode head) {
        ListNode prev=null;
        ListNode curr=head;
        ListNode next;
        if(head==null){
            return true;
        }
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            curr=next;
            prev=curr;
        }

        //正向链表head和反向链表
        while(head!=null && prev!=null){
            if(head.val!=prev.val){
                return false;
            }
            head=head.next;
            prev=prev.next;
        }
        return true;


    }

}
