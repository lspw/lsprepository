package com.lsp.crm.workbench.service.Impl;

import com.lsp.crm.workbench.mapper.ActivityMapper;
import com.lsp.crm.workbench.mapper.ActivityRemarkMapper;
import com.lsp.crm.workbench.pojo.ActivityRemark;
import com.lsp.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("activityRemarkService")
public class ActivityRemarkServiceImpl implements ActivityRemarkService {
    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;

    @Override
    public List<ActivityRemark> queryActivityRemarkForDetailById(String activityId) {
        return activityRemarkMapper.selectActivityRemarkForDetailById(activityId);
    }

    @Override
    public int saveCreateActivityRemark(ActivityRemark remark) {
        return activityRemarkMapper.insertActivityRemark(remark);
    }

    @Override
    public int deleteActivityRemarkById(String id) {
        return activityRemarkMapper.deleteActivityRemarkById(id);
    }

    @Override
    public int updateActivityRemarkById(ActivityRemark remark) {
        return activityRemarkMapper.updateActivityRemark(remark);
    }
}
