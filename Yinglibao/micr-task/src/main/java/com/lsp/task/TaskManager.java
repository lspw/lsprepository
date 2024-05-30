package com.lsp.task;

import com.lsp.commons.utils.HttpClientUtils;
import com.lsp.ylb.api.service.IncomeServer;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("taskManager")
public class TaskManager {
    /*
    * 定义方法，表示要执行的定时任务
    * 方法定义的要求：
    * public 公共方法
    * 方法没有参数
    * 方法没有返回值
    * 方法上面加注解@Scheduled
    * */
   /* @Scheduled(cron="30 04 15 * * ?")
    public void testCron(){
        System.out.println("定时任务执行了："+new Date());
    }
*/

    //@Scheduled(cron="*/5 * * * * ?")
    /*public void testCron(){
        System.out.println("定时任务执行了："+new Date());
    }*/


    @DubboReference(interfaceClass = IncomeServer.class,version = "1.0-SNAPSHOT")
    private IncomeServer incomeServer;

    @Scheduled(cron="0 0 1 * * ?")
    public void invokeGenerateIncomePlan(){
        incomeServer.generateIncomePlan();
    }
    //收益返还
    @Scheduled(cron="0 0 1 * * ?")
    public void invokeGenerateIncomeBack(){
        incomeServer.generateIncomePlan();
    }

    //补单接口
    @Scheduled(cron="0 0/20 * * * ?")
    public void invokeKuaiQingQuery(){
        try {
            String url="http://localhost:9000/pay/fastmoney/rece/query";
            HttpClientUtils.doGet(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
