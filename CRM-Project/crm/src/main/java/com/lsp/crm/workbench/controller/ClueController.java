package com.lsp.crm.workbench.controller;

import com.lsp.crm.commons.contants.Contants;
import com.lsp.crm.commons.pojo.ReturnObject;
import com.lsp.crm.commons.utils.DateFormatUtils;
import com.lsp.crm.commons.utils.UUIDUtils;
import com.lsp.crm.settings.pojo.DicValue;
import com.lsp.crm.settings.pojo.User;
import com.lsp.crm.settings.service.DicValueService;
import com.lsp.crm.settings.service.UserService;
import com.lsp.crm.workbench.mapper.ActivityMapper;
import com.lsp.crm.workbench.pojo.Activity;
import com.lsp.crm.workbench.pojo.Clue;
import com.lsp.crm.workbench.pojo.ClueActivityRelation;
import com.lsp.crm.workbench.pojo.ClueRemark;
import com.lsp.crm.workbench.service.ActivityService;
import com.lsp.crm.workbench.service.ClueActivityRelationService;
import com.lsp.crm.workbench.service.ClueRemarkService;
import com.lsp.crm.workbench.service.ClueService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class ClueController {


    @Autowired
    private UserService userService;
    @Autowired
    private DicValueService dicValueService;
    @Autowired
    private ClueService clueService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ClueRemarkService clueRemarkService;
    @Autowired
    private ClueActivityRelationService clueActivityRelationService;

    @RequestMapping("/workbench/clue/toIndex")
    public String toIndex(HttpServletRequest request){
        //调用service层方法查询数据
        List<User> userList=userService.queryAllUser();
        List<DicValue> appellation=dicValueService.queryDicValueByTypeCode("appellation");
        List<DicValue> clueState = dicValueService.queryDicValueByTypeCode("clueState");
        List<DicValue> source = dicValueService.queryDicValueByTypeCode("source");
        //查询所有的线索
        List<Clue> clue= clueService.queryAllClue();


        request.setAttribute("userList",userList);
        request.setAttribute("appellation",appellation);
        request.setAttribute("clueState",clueState);
        request.setAttribute("source",source);
        request.setAttribute("clue",clue);
        return "workbench/clue/index";

    }

    @RequestMapping("/workbench/clue/saveClue")
    @ResponseBody
    public Object saveClue(Clue clue, HttpSession session){
        User user=(User) session.getAttribute(Contants.SESSION_USER);
        //进一步封装参数
        clue.setCreateTime(DateFormatUtils.formatUtil(new Date()));
        clue.setCreateBy(user.getId());
        clue.setId(UUIDUtils.getUuid());

        ReturnObject returnObject=new ReturnObject();

        try{
            int ret = clueService.saveCreateClue(clue);
            if (ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后。。。。");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后。。。。");
        }
       return returnObject;
    }
    @RequestMapping("/workbench/clue/detailClue")
    public String detailClue(String id,HttpServletRequest request){
        Clue clue=clueService.queryClueDetailById(id);
        List<ClueRemark> remarkList=clueRemarkService.queryClueRemarkForDetailByClueId(id);
        List<Activity> activityList=activityService.queryActivityForDetailByClueId(id);

        request.setAttribute("clue",clue);
        request.setAttribute("remarkList",remarkList);
        request.setAttribute("activityList",activityList);
        //请求转发
        return "workbench/clue/detail";




    }
    @RequestMapping("/workbench/clue/saveBund")
    @ResponseBody
    public Object saveBund(String[] activityId,String clueId) {
        //封装参数
        ClueActivityRelation car=null;
        List<ClueActivityRelation> relationList=new ArrayList<>();
        for(String ai:activityId){
            car=new ClueActivityRelation();
            car.setActivityId(ai);
            car.setClueId(clueId);
            car.setId(UUIDUtils.getUuid());
            relationList.add(car);
        }
        ReturnObject returnObject=new ReturnObject();
        try{
            //调用service层方法
            int ret = clueActivityRelationService.saveCreateClueActivityRelationByList(relationList);
            if (ret>0){
                //保存成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                //关联成功刷新刷新列表（重新查询表）
                List<Activity> activityList=activityService.queryActivityForDetailByIds(activityId);
                returnObject.setRetData(activityList);
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后。。。");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后。。。");
        }
        return returnObject;

    }
    @RequestMapping("/workbench/clue/deleteClue")
    @ResponseBody
    public Object deleteClue(ClueActivityRelation relation){
        //调用service层方法删除数据
        ReturnObject returnObject=new ReturnObject();
        try{
            int ret = clueActivityRelationService.deleteClueActivityRelationByClueIdActivityId(relation);
            if (ret>0){
                //删除成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                //删除失败
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后。。。");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后。。。");
        }

        return returnObject;
    }

    @RequestMapping("/workbench/clue/toConvert")

    public String toConvert(String id,HttpServletRequest request){
        //调用service方法
        Clue clue = clueService.queryClueDetailById(id);
        List<DicValue> dicValueList = dicValueService.queryDicValueByTypeCode("stage");
        request.setAttribute("clue",clue);
        request.setAttribute("dicValueList",dicValueList);
        //请求转发
        return "workbench/clue/convert";
    }

    @RequestMapping("/workbench/clue/convertClue")
    @ResponseBody
    public Object convertClue(String clueId,String money,String name,String expectedDate,String stage,String activityId,String isCreateTran
    ,HttpSession session){
        Map<String,Object> map=new HashMap<>();
        map.put("clueId",clueId);
        map.put("money",money);
        map.put("name",name);
        map.put("expectedDate",expectedDate);
        map.put("stage",stage);
        map.put("activityId",activityId);
        map.put("isCreateTran",isCreateTran);
        map.put(Contants.SESSION_USER,session.getAttribute(Contants.SESSION_USER));
        ReturnObject returnObject=new ReturnObject();
        try{
            clueService.saveCovert(map);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后。。。");
        }
       return returnObject;
    }

    @RequestMapping("/workbench/clue/queryClueByCondition")
    @ResponseBody
    public Object queryClueByCondition(String fullname,String owner,String company,String phone,
                                       String mphone,String state,String source,int pageNo,
                                       int pageSize){
        //调用service方法查询数据
        Map<String,Object> map=new HashMap<>();
        map.put("fullname",fullname);
        map.put("owner",owner);
        map.put("company",company);
        map.put("mphone",mphone);
        map.put("phone",phone);
        map.put("state",state);
        map.put("source",source);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);

        List<Clue> clueList = clueService.queryClueByCondition(map);
        int totalRows = clueService.queryClueCountByCondition(map);
        //将数据以json的形式传输给前台
        Map<String,Object> retMap=new HashMap<>();
        retMap.put("clueList",clueList);
        retMap.put("totalRows",totalRows);
        return retMap;
    }

}
