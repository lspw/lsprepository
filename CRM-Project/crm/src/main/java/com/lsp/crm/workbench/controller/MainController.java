package com.lsp.crm.workbench.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/workbench/main/index")
    public String index(){
        //跳转到main/index.jsp
        return "workbench/main/index";
    }
}
