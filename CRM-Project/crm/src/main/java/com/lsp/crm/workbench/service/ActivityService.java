package com.lsp.crm.workbench.service;

import com.lsp.crm.workbench.pojo.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityService {

    int saveCreateActivity(Activity activity);

    List<Activity> queryActivityByConditionForPage(Map<String,Object> map);
    /*根据条件查询市场活动*/
    int queryCountOfActivityByCondition(Map<String,Object> map);
    /*根据id删除市场活动*/
    int deleteActivityById(List<String> id);

    Activity queryActivityById(String id);

    int saveEditActivity(Activity activity);
    List<Activity> queryAllActivitys();

    int saveCreateActivityByList(List<Activity> activitiyList);

    //查询市场活动的明细
    Activity queryActivityForDetailById(String id);

    List<Activity> queryActivityForDetailByClueId(String clueId);

    //关联查询
    List<Activity> queryActivityForDetailByNameClueId(Map<String,Object> map);

    List<Activity> queryActivityForDetailByIds(String[] ids);

    List<Activity> queryActivityConvertByNameClueId(Map<String,Object> map);

    List<Activity> querySomeActivity(List<String> id);
}
