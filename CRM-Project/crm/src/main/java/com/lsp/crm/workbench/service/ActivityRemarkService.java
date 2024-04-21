package com.lsp.crm.workbench.service;

import com.lsp.crm.workbench.pojo.ActivityRemark;

import java.util.List;

public interface ActivityRemarkService {
    List<ActivityRemark> queryActivityRemarkForDetailById(String activityId);
    /*保存市场活动的备注*/
    int saveCreateActivityRemark(ActivityRemark remark);
    //删除市场活动备注
    int deleteActivityRemarkById(String id);
    //更新备注
    int updateActivityRemarkById(ActivityRemark remark);

}
