package com.lsp.crm.workbench.controller;

import com.lsp.crm.commons.contants.Contants;
import com.lsp.crm.commons.pojo.ReturnObject;
import com.lsp.crm.commons.utils.DateFormatUtils;
import com.lsp.crm.commons.utils.UUIDUtils;
import com.lsp.crm.settings.pojo.User;
import com.lsp.crm.workbench.pojo.ActivityRemark;
import com.lsp.crm.workbench.service.ActivityRemarkService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class ActivityRemarkController {
    @Autowired
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("/workbench/activity/saveCreateActivityRemark")
    @ResponseBody
    public Object saveCreateActivityRemark(ActivityRemark remark, HttpSession session){


        User user=(User)session.getAttribute(Contants.SESSION_USER);
        //封装参数
        remark.setId(UUIDUtils.getUuid());
        remark.setCreateTime(DateFormatUtils.formatUtil(new Date()));
        remark.setCreateBy(user.getId());
        remark.setEditFlag(Contants.REMARK_EDIT_NO);
        //调用service层方法，保存创建的备注
        ReturnObject returnObject=new ReturnObject();
        try{
            int ret=activityRemarkService.saveCreateActivityRemark(remark);
            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setRetData(remark);
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
    @RequestMapping("/workbench/activity/deleteRemark")
    @ResponseBody
    public Object deleteRemark(String id){

        ReturnObject returnObject=new ReturnObject();
        //调用service层方法删除备注
        try{
           int ret = activityRemarkService.deleteActivityRemarkById(id);
           if(ret>0){
               //删除成功
               returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);

           }else{
               //删除失败
               returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
               returnObject.setMessage("系统忙，请稍后重试。。。");
           }

        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试。。。");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/updateActivityRemark")
    @ResponseBody
    public Object updateActivityRemark(ActivityRemark remark,HttpSession session){
        User user=(User) session.getAttribute(Contants.SESSION_USER);
        //封装参数
        remark.setEditTime(DateFormatUtils.formatUtil(new Date()));
        remark.setEditBy(user.getId());
        remark.setEditFlag(Contants.REMARK_EDIT_YES);

        ReturnObject returnObject=new ReturnObject();
        try{
           int  ret = activityRemarkService.updateActivityRemarkById(remark);
           if(ret>0){
               //更新成功
               returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
               returnObject.setRetData(remark);
           }else{
               //更新失败
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
}
