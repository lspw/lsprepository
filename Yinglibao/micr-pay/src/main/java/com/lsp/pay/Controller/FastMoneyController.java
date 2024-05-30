package com.lsp.pay.Controller;


import com.lsp.pay.service.FastMoneyService;
import com.lsp.ylb.api.pojo.User;
import io.netty.handler.codec.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

/*
* @Controller和@RestController的区别：
* @Controller标记的控制器类返回值是一个字符串（视图的地址）
* @RestController标记的控制器类的返回值是一个对象，而且，@RestController默认所有的方法都是@ResponseBody 所以可以
* 序列化json和xml类型的数据。通常不会返回视图而是返回数据
* */
@Controller
@RequestMapping("/fastmoney")
public class FastMoneyController {
    @Resource
    private FastMoneyService fastMoneyService;

    //接受vue前端发送来的请求
    @GetMapping("/rece/recharge")
    public String receFrontRechargeFastMoney(Integer uid,
                                             BigDecimal rechargeMoney,
                                             Model model){
        //默认登陆视图为错误视图
        String view="err";
        if ((uid!=null && uid>0)&&(rechargeMoney!=null && rechargeMoney.doubleValue()>0)){
            User user = fastMoneyService.theUserIsExistence(uid);
            //检查uid是否为有效用户
          try{
              if (user!=null){
                  //创建块钱支付接口需要的有效请求参数
                  Map<String, String> data = fastMoneyService.generateFormDate(uid, user.getPhone(), rechargeMoney);
                  model.addAllAttributes(data);
                  //创建充值记录
                  fastMoneyService.addRecharge(uid,rechargeMoney,data.get("orderId"));
                  //八订单号存放到redis中
                  fastMoneyService.addOrderIdToRedis(data.get("orderId"));
              }
              view="FastMoneyForm";
          }catch (Exception e){
              e.printStackTrace();
          }
        }
        return  view;
    }

    //接受块钱给商家的支付结果，块钱get方式发送请求给商家
    @GetMapping("/rece/notify")
    public String payResultNotify(HttpServletRequest request){
        System.out.println("=================接收快钱的异步通知=============");
        fastMoneyService.kqNotify(request);
        return "<result>1</result><redirecturl>http://localhost:8080/</redirecturl>";
    }

    //从定时任务，调用接口
    @GetMapping("/rece/query")
    @ResponseBody
    public String queryKQOrder(){
        fastMoneyService.handleQueryOrder();
        return "接受了查询的请求";
    }

}
