package com.lsp.crm.workbench.controller;

import com.lsp.crm.commons.contants.Contants;
import com.lsp.crm.commons.pojo.ReturnObject;
import com.lsp.crm.commons.utils.DateFormatUtils;
import com.lsp.crm.commons.utils.HSSFUtils;
import com.lsp.crm.commons.utils.UUIDUtils;
import com.lsp.crm.settings.pojo.User;
import com.lsp.crm.settings.service.UserService;
import com.lsp.crm.workbench.pojo.Activity;
import com.lsp.crm.workbench.pojo.ActivityRemark;
import com.lsp.crm.workbench.service.ActivityRemarkService;
import com.lsp.crm.workbench.service.ActivityService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.http.HttpRequest;
import java.util.*;

@Controller
public class ActivityController {
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityRemarkService activityRemarkService;
    @RequestMapping("/workbench/activity/index")
    public String index(HttpServletRequest request){
        List<User> userList = userService.queryAllUser();
        //将数据存到request作用域中
        request.setAttribute("userList",userList);
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/saveCreateActivity")
    public @ResponseBody Object saveCreateActivity(Activity activity, HttpSession session){

        //封装参数
        activity.setId(UUIDUtils.getUuid());
        activity.setCreateTime(DateFormatUtils.formatUtil(new Date()));
        activity.setCreateBy(((User)session.getAttribute(Contants.SESSION_USER)).getId());
        //调用service层方法保存市场活动
        //看是否由异常
        ReturnObject returnObject=new ReturnObject();
        try{
            int count = activityService.saveCreateActivity(activity);
            //无异常判断是否添加成功
            if (count>0) {
                //添加成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                //添加失败
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setMessage("系统忙,请稍后重试...");
            }
        }catch (Exception e){
            e.printStackTrace();
            //添加失败
            returnObject.setMessage("系统忙,请稍后重试...");
        }

        System.out.println("success");
        return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivityByConditionForPage")
    @ResponseBody
    public  Object  queryActivityByConditionForPage(String name,String owner,String startDate,
                                                                 String endDate,int pageNo,int pageSize){
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        //调用service层查询数据
        List<Activity> activitiesList = activityService.queryActivityByConditionForPage(map);
        int totalRows = activityService.queryCountOfActivityByCondition(map);
        //将数据以json的形式传输给前台
        Map<String,Object> retMap=new HashMap<>();
        retMap.put("activitiesList",activitiesList);
        retMap.put("totalRows",totalRows);
        return retMap;
    }
    //删除数据

    @RequestMapping("/workbench/activity/deleteActivityIds")
    public @ResponseBody Object deleteActivityIds(String[] ids){
        List<String> id=new ArrayList<>();
        for(String id1:ids){
            if(ids.length==1){
                id.add(id1.substring(0,id1.length()));
            }else{
                id.add(id1.substring(1,id1.length()));
            }
        }

        ReturnObject returnObject=new ReturnObject();
        try{

            int ret = activityService.deleteActivityById(id);
            if(ret>0){
                //删除成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                //删除失败
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                //提示信息
                returnObject.setMessage("系统忙，请稍后重试...");
            }
        }catch (Exception e){
            e.printStackTrace();
            //有异常，删除失败报提示信息
            returnObject.setMessage("系统忙，请稍后重试...");

        }
        return returnObject;
    }

    /*根据id查询响应的市场活动信息*/
    @RequestMapping("/workbench/activity/queryActivityById")
    @ResponseBody
    public  Object queryActivityById(String id){
       Activity activity = activityService.queryActivityById(id);
        return activity;
    }
    @RequestMapping("/workbench/activity/saveEditActivity")
    @ResponseBody
    public  Object saveEditActivity(Activity activity,HttpSession session){
        User user =(User)session.getAttribute(Contants.SESSION_USER);
        //封装参数
        ReturnObject returnObject=new ReturnObject();
        activity.setEditTime(DateFormatUtils.formatUtil(new Date()));
        try{
            int ret = activityService.saveEditActivity(activity);
            if(ret>0){
                //修改成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                //修改失败
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请重试...");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请重试...");
        }
        return returnObject;
    }
 @RequestMapping("/workbench/activity/exportAllActivitys")
    public void exportAllActivitys(HttpServletResponse response) throws IOException {
        //调用service层方法，查询所有的市场后动
     List<Activity> activitieyList = activityService.queryAllActivitys();
     //创建exel文件，并且把所有的activitys的数据写道excel文件中
     HSSFWorkbook wb=new HSSFWorkbook();
     //创建页
     HSSFSheet sheet = wb.createSheet("市场活动表");
     //在创建的页中
     HSSFRow row = sheet.createRow(0);//创建列
     HSSFCell cell = row.createCell(0);//创建列
     cell.setCellValue("ID");//列名
     cell=row.createCell(1);
     cell.setCellValue("所有者");//列名
     cell=row.createCell(2);
     cell.setCellValue("名称");//列名
     cell=row.createCell(3);
     cell.setCellValue("开始日期");//列名
     cell=row.createCell(4);
     cell.setCellValue("结束日期");//列名
     cell=row.createCell(5);
     cell.setCellValue("成本");//列名
     cell=row.createCell(6);
     cell.setCellValue("描述");//列名
     cell=row.createCell(7);
     cell.setCellValue("创建时间");//列名
     cell=row.createCell(8);
     cell.setCellValue("创建者");//列名
     cell=row.createCell(9);
     cell.setCellValue("修改时间");//列名
     cell=row.createCell(10);
     cell.setCellValue("修改者");//列名
     cell=row.createCell(11);

     //遍历activityList,创建HSSRow对象，生成所有的数据行

    //判断List是否为空
     if(activitieyList!=null && activitieyList.size()>0){
         Activity activity=null;
         for(int i=0;i<activitieyList.size();i++){
             activity = activitieyList.get(i);

             //没遍历一行activity 生成一行
             row = sheet.createRow(i+1);//创建行
             cell = row.createCell(0);//创建列
             cell.setCellValue(activity.getId());//列名
             cell=row.createCell(1);
             cell.setCellValue(activity.getOwner());//列名
             cell=row.createCell(2);
             cell.setCellValue(activity.getName());//列名
             cell=row.createCell(3);
             cell.setCellValue(activity.getStartDate());//列名
             cell=row.createCell(4);
             cell.setCellValue(activity.getEndDate());//列名
             cell=row.createCell(5);
             cell.setCellValue(activity.getCost());//列名
             cell=row.createCell(6);
             cell.setCellValue(activity.getDescription());//列名
             cell=row.createCell(7);
             cell.setCellValue(activity.getCreateTime());//列名
             cell=row.createCell(8);
             cell.setCellValue(activity.getCreateBy());//列名
             cell=row.createCell(9);
             cell.setCellValue(activity.getEditTime());//列名
             cell=row.createCell(10);
             cell.setCellValue(activity.getEditBy());//列名
             cell=row.createCell(11);
         }
     }
        //根据wb对象生成excel文件
    /* OutputStream os=new FileOutputStream("D:\\JavaWeb\\project_inf\\CRM_inf\\程序文件\\activityFile.xls");
     wb.write(os);
     //关闭资源
     os.close();
     wb.close();*/
     //把生成的excel文件下载到客户端
     response.setContentType("application/octet-stream;charset=UTF-8");
     response.addHeader("Content-Disposition","attachment;filename=activityList.xls");
     OutputStream out=response.getOutputStream();
    /* InputStream is=new FileInputStream("D:\\JavaWeb\\project_inf\\CRM_inf\\程序文件\\activityFile.xls");

     byte[] buff=new byte[256];
     int len=0;
     while ((len=is.read(buff))!=-1) {
         out.write(buff,0,len);
     }
     is.close();*/
     wb.write(out);
     wb.close();
     out.flush();
 }

 /*导出部分市场活动*/
    @RequestMapping("/workbench/activity/exportSomeActivitys")
    @ResponseBody
    public void exportSomeActivitys(String[] ids, HttpServletResponse response) throws IOException {

        List<String> id=new ArrayList<>();
        for(String id1:ids){
            if(ids.length==1){
                id.add(id1.substring(0,id1.length()));
            }else{
                id.add(id1.substring(1,id1.length()));
            }
        }

        //调用service层方法，查询所有的市场后动
        List<Activity> activitieyList = activityService.querySomeActivity(id);
        //创建exel文件，并且把所选的activitys的数据写道excel文件中
        HSSFWorkbook wb=new HSSFWorkbook();
        //创建页
        HSSFSheet sheet = wb.createSheet("市场活动表");
        //在创建的页中
        HSSFRow row = sheet.createRow(0);//创建列
        HSSFCell cell = row.createCell(0);//创建列
        cell.setCellValue("ID");//列名
        cell=row.createCell(1);
        cell.setCellValue("所有者");//列名
        cell=row.createCell(2);
        cell.setCellValue("名称");//列名
        cell=row.createCell(3);
        cell.setCellValue("开始日期");//列名
        cell=row.createCell(4);
        cell.setCellValue("结束日期");//列名
        cell=row.createCell(5);
        cell.setCellValue("成本");//列名
        cell=row.createCell(6);
        cell.setCellValue("描述");//列名
        cell=row.createCell(7);
        cell.setCellValue("创建时间");//列名
        cell=row.createCell(8);
        cell.setCellValue("创建者");//列名
        cell=row.createCell(9);
        cell.setCellValue("修改时间");//列名
        cell=row.createCell(10);
        cell.setCellValue("修改者");//列名
        cell=row.createCell(11);

        //遍历activityList,创建HSSRow对象，生成所有的数据行

        //判断List是否为空
        if(activitieyList!=null && activitieyList.size()>0){
            Activity activity=null;
            for(int i=0;i<activitieyList.size();i++){
                activity = activitieyList.get(i);

                //没遍历一行activity 生成一行
                row = sheet.createRow(i+1);//创建行
                cell = row.createCell(0);//创建列
                cell.setCellValue(activity.getId());//列名
                cell=row.createCell(1);
                cell.setCellValue(activity.getOwner());//列名
                cell=row.createCell(2);
                cell.setCellValue(activity.getName());//列名
                cell=row.createCell(3);
                cell.setCellValue(activity.getStartDate());//列名
                cell=row.createCell(4);
                cell.setCellValue(activity.getEndDate());//列名
                cell=row.createCell(5);
                cell.setCellValue(activity.getCost());//列名
                cell=row.createCell(6);
                cell.setCellValue(activity.getDescription());//列名
                cell=row.createCell(7);
                cell.setCellValue(activity.getCreateTime());//列名
                cell=row.createCell(8);
                cell.setCellValue(activity.getCreateBy());//列名
                cell=row.createCell(9);
                cell.setCellValue(activity.getEditTime());//列名
                cell=row.createCell(10);
                cell.setCellValue(activity.getEditBy());//列名
                cell=row.createCell(11);
            }
        }
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.addHeader("Content-Disposition","attachment;filename=activityList.xls");
        OutputStream out=response.getOutputStream();
        wb.write(out);
        wb.close();
        out.flush();

    }
 @RequestMapping("/workbench/activity/importActivity")
 public @ResponseBody Object importActivity(MultipartFile activityFile,HttpSession session){
     User user=(User) session.getAttribute(Contants.SESSION_USER);
     ReturnObject returnObject=new ReturnObject();
     try {
         InputStream is=activityFile.getInputStream();
         HSSFWorkbook wb=new HSSFWorkbook(is);
         //根据wb获取HSSFSheet对象，封装了一页的所有信息
         HSSFSheet sheet=wb.getSheetAt(0);//页的下标，下标从0开始，依次增加
         //根据sheet获取HSSFRow对象，封装了一行的所有信息
         HSSFRow row=null;
         HSSFCell cell=null;
         Activity activity=null;
         List<Activity> activityList=new ArrayList<>();
         for(int i=1;i<=sheet.getLastRowNum();i++) {//sheet.getLastRowNum()：最后一行的下标
             row=sheet.getRow(i);//行的下标，下标从0开始，依次增加
             activity=new Activity();
             activity.setId(UUIDUtils.getUuid());
             activity.setOwner(user.getId());
             activity.setCreateTime(DateFormatUtils.formatUtil(new Date()));
             activity.setCreateBy(user.getId());

             for(int j=0;j<row.getLastCellNum();j++) {//row.getLastCellNum():最后一列的下标+1
                 //根据row获取HSSFCell对象，封装了一列的所有信息
                 cell=row.getCell(j);//列的下标，下标从0开始，依次增加

                 //获取列中的数据
                 String cellValue=HSSFUtils.getCellValueForStr(cell);
                 if(j==0){
                     activity.setName(cellValue);
                 }else if(j==1){
                     activity.setStartDate(cellValue);
                 }else if(j==2){
                     activity.setEndDate(cellValue);
                 }else if(j==3){
                     activity.setCost(cellValue);
                 }else if(j==4){
                     activity.setDescription(cellValue);
                 }
             }

             //每一行中所有列都封装完成之后，把activity保存到list中
             activityList.add(activity);
         }

         //调用service层方法，保存市场活动
         int ret=activityService.saveCreateActivityByList(activityList);

         returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
         returnObject.setRetData(ret);
     }catch (Exception e){
         e.printStackTrace();
         returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
         returnObject.setMessage("系统忙，请稍后重试....");
     }

     return returnObject;
 }
 //跳转到市场活动的详情页面
    @RequestMapping("/workbench/activity/toDetail")
    public String toDetail(String id , HttpServletRequest request){
        //调用service层方法查询数据
        Activity activity = activityService.queryActivityForDetailById(id);
        List<ActivityRemark> remarkList = activityRemarkService.queryActivityRemarkForDetailById(id);
        request.setAttribute("activity",activity);
        request.setAttribute("remarkList",remarkList);
        //请求转发
        return "workbench/activity/detail";
    }

    @RequestMapping("/workbench/clue/queryActivityForDetailByNameClueId")
    @ResponseBody
    public Object queryActivityForDetailByNameClueId(String activityName,String clueId){
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("activityName",activityName);
        map.put("clueId",clueId);
        List<Activity> activityList=activityService.queryActivityForDetailByNameClueId(map);
        return activityList;
    }

    @RequestMapping("/workbench/clue/queryActivityConvertByNameClueId")
    @ResponseBody
    public Object queryActivityConvertByNameClueId(String activityName,String clueId){
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("activityName",activityName);
        map.put("clueId",clueId);
        //调用service方法
        List<Activity> activityList = activityService.queryActivityConvertByNameClueId(map);

        return activityList;


    }
}


