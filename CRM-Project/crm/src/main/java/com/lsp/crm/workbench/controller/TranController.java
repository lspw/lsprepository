package com.lsp.crm.workbench.controller;

import com.lsp.crm.commons.contants.Contants;
import com.lsp.crm.commons.pojo.ReturnObject;
import com.lsp.crm.settings.mapper.DicValueMapper;
import com.lsp.crm.settings.pojo.DicValue;
import com.lsp.crm.settings.pojo.User;
import com.lsp.crm.settings.service.DicValueService;
import com.lsp.crm.settings.service.UserService;
import com.lsp.crm.workbench.pojo.Tran;
import com.lsp.crm.workbench.pojo.TranHistory;
import com.lsp.crm.workbench.pojo.TranRemark;
import com.lsp.crm.workbench.service.CustomerService;
import com.lsp.crm.workbench.service.TranHistoryService;
import com.lsp.crm.workbench.service.TranRemarkService;
import com.lsp.crm.workbench.service.TranService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Controller
public class TranController {
    @Autowired
    private DicValueService dicValueService;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TranService tranService;
    @Autowired
    private TranHistoryService tranHistoryService;
    @Autowired
    private TranRemarkService tranRemarkService;

    @RequestMapping("/workbench/transaction/index")
    public String index(HttpServletRequest request){
        //调用service方法查询查询中的下拉列表中的值
        List<DicValue> stageList = dicValueService.queryDicValueByTypeCode("stage");
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");
        List<DicValue> transactionTypeList = dicValueService.queryDicValueByTypeCode("transactionType");

        request.setAttribute("stageList",stageList);
        request.setAttribute("sourceList",sourceList);
        request.setAttribute("transactionTypeList",transactionTypeList);
       return  "workbench/transaction/index";
    }

    @RequestMapping("/workbench/transaction/toSave")
    public String toSave(HttpServletRequest request){
        //调用service方法
        List<User> usersList = userService.queryAllUser();
        List<DicValue> stageList = dicValueService.queryDicValueByTypeCode("stage");
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");
        List<DicValue> transactionTypeList = dicValueService.queryDicValueByTypeCode("transactionType");

        request.setAttribute("userList",usersList);
        request.setAttribute("stageList",stageList);
        request.setAttribute("sourceList",sourceList);
        request.setAttribute("transactionTypeList",transactionTypeList);
        return "workbench/transaction/save";
    }
    @RequestMapping("/workbench/transaction/getPossiblityFile")
    @ResponseBody
    public Object getPossiblityFile(String stageValue){
        //解析properties配置文件
        ResourceBundle bundle=ResourceBundle.getBundle("possiblity");
        String  possiblity= bundle.getString(stageValue);
        return possiblity;
    }
    @RequestMapping("/workbench/transaction/queryAllCustomerName")
    public @ResponseBody Object queryAllCustomerName(String customerName){
        List<String> name = customerService.queryAllCustomerName(customerName);
        return name;
    }


    @RequestMapping("/workbench/transaction/saveTran")
    @ResponseBody
    public Object saveTran(@RequestParam Map<String,Object> map, HttpSession session){
        map.put(Contants.SESSION_USER,session.getAttribute(Contants.SESSION_USER));
        ReturnObject returnObject=new ReturnObject();
        try{
            //调用service方法保存交易xinxi
            tranService.queryCustomerByName(map);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        }catch (Exception e){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后。。。");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/transaction/detail")
    public String detail(String id,HttpServletRequest request){
        //调用service层方法查询数据
        Tran tranList = tranService.queryTranForDetailById(id);
        List<TranHistory> tranHistoriesList = tranHistoryService.queryTranHistoryForDetailByTranId(id);
        List<TranRemark> tranRemarksList = tranRemarkService.queryTranRemarkForDetailByTranId(id);

        //在配置文件中查询交易的可能性
        ResourceBundle bundle=ResourceBundle.getBundle("possiblity");
        String possiblity=bundle.getString(tranList.getStage());

        request.setAttribute("possiblity",possiblity);
        request.setAttribute("tranList",tranList);
        request.setAttribute("tranHistoriesList",tranHistoriesList);
        request.setAttribute("tranRemarksList",tranRemarksList);

        //调用service层方法查询交易所有的阶段
        List<DicValue> dicValueList = dicValueService.queryDicValueByTypeCode("stage");
        request.setAttribute("dicValueList",dicValueList);

        return "workbench/transaction/detail";



    }
}
