package com.lsp.commons.utils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * 验证pagesize 和 pageno 的工具类
 * */
public class CommonUtil {
    /*处理pagesize*/
    public static int defaultPageSize(Integer pageSize) {
        int pSize = pageSize;
        if (pageSize == null || pageSize < 1) {
            pSize = 1;
        }
        return pSize;
    }

    /*处理pageno*/
    public static int defaultPageNo(Integer pageNo) {
        int pNo = pageNo;
        if (pageNo == null || pageNo < 0) {
            pNo = 1;
        }
        return pNo;
    }

    /*手机号脱敏*/
    public static String tuoMinPhone(String phone) {
        String result = "***********";
        if (phone != null && phone.trim().length() == 11) {
            result = phone.substring(0, 3) + "******" + phone.substring(9, 11);
        }
        return result;
    }

    /*正则表达式验证手机号格式*/
    public static boolean checkPhone(String phone) {
        boolean flag = false;
        if (phone != null && phone.length() == 11) {
            flag = Pattern.matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", phone);
        }
        return flag;
    }
    /*比较BidDecimal n1>=n2为true  else  false*/
    public static boolean ge(BigDecimal n1,BigDecimal n2){
        if (n1==null && n2==null){
            throw new RuntimeException("数据无效");
        }
        return n1.compareTo(n2)>=0;

    }


}