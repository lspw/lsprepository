package com.lsp.crm.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
* 对Data类型的数据进行格式化处理的工具类
* */
public class DateFormatUtils {
    public static String formatUtil(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataStr = sdf.format(date);
        return dataStr;
    }
}
