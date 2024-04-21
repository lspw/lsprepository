package com.lsp.crm.workbench.service.Impl;

import com.lsp.crm.workbench.mapper.ActivityMapper;

import com.lsp.crm.workbench.pojo.Activity;
import com.lsp.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Override
    public int saveCreateActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }

    @Override
    public List<Activity> queryActivityByConditionForPage(Map<String, Object> map) {
        return activityMapper.selectActivityByConditionForPage(map);
    }

    @Override
    public int queryCountOfActivityByCondition(Map<String, Object> map) {
        return activityMapper.selectCountActivityByCondition(map);
    }

    @Override
    public int deleteActivityById(List<String> id) {
        return activityMapper.deleteActivityById(id);
    }

    @Override
    public Activity queryActivityById(String id) {
        return activityMapper.selectActivityById(id);
    }


    @Override
    public int saveEditActivity(Activity activity) {
        return activityMapper.updateActivity(activity);
    }



    @Override
    public List<Activity> queryAllActivitys() {
        return activityMapper.selectAllActivitys();
    }

    @Override
    public int saveCreateActivityByList(List<Activity> activitiyList) {
        return activityMapper.insertActivityByList(activitiyList );
    }

    @Override
    public Activity queryActivityForDetailById(String id) {
        return activityMapper.selectActivityDetailById(id);
    }

    @Override
    public List<Activity> queryActivityForDetailByClueId(String clueId) {
        return activityMapper.selectActivityForDetailByClueId(clueId);
    }

    @Override
    public List<Activity> queryActivityForDetailByNameClueId(Map<String,Object> map) {
        return activityMapper.selectActivityForDetailByNameClueId(map);
    }

    @Override
    public List<Activity> queryActivityForDetailByIds(String[] ids) {
        return activityMapper.selectActivityForDetail(ids);
    }

    @Override
    public List<Activity> queryActivityConvertByNameClueId(Map<String, Object> map) {
        return activityMapper.selectActivityForConvertByNameClueId(map);
    }

    @Override
    public List<Activity> querySomeActivity(List<String> id) {
        return activityMapper.selectSomeActivityById(id);
    }


}

