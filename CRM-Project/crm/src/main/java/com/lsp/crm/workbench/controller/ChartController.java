package com.lsp.crm.workbench.controller;

import com.lsp.crm.workbench.pojo.FunnelVO;
import com.lsp.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ChartController {
    @Autowired
    private TranService tranService;


    @RequestMapping("/workbench/chart/transaction/index")
    public String index(){
        //跳转页面
        return "workbench/chart/transaction/index";
    }

    @RequestMapping("/workbench/chart/transaction/queryCountOfTranGroupByStage")
    @ResponseBody
    public Object queryCountOfTranGroupByStage(){
        //调用service层方法
        List<FunnelVO> funnelVOSList = tranService.queryCountOfTranGroupByStage();
        return funnelVOSList;

    }
}
