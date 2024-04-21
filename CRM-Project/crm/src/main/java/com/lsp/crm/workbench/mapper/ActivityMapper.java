package com.lsp.crm.workbench.mapper;

import com.lsp.crm.workbench.pojo.Activity;
import com.lsp.crm.workbench.pojo.ActivityExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ActivityMapper {
    long countByExample(ActivityExample example);

    int deleteByExample(ActivityExample example);

    int deleteByPrimaryKey(String id);

    int insertSelective(Activity record);

    List<Activity> selectByExample(ActivityExample example);

    Activity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Activity record, @Param("example") ActivityExample example);

    int updateByExample(@Param("record") Activity record, @Param("example") ActivityExample example);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    /*添加市场活动*/
    int insertActivity(Activity activity);

    /*显示市场活动*/
    List<Activity> selectActivityByConditionForPage(Map<String,Object> map);
    /*显示总条数*/
    int selectCountActivityByCondition(Map<String,Object> map);
    //删除数据
    int deleteActivityById(List<String> id);
    /*根据id查找相应的市场活动*/
    Activity selectActivityById(String id);

    /**
     * 保存修改的的市场活动
     * */
    int updateActivity(Activity activity);

    /*查询所有的市场活动的数据*/
    List<Activity> selectAllActivitys();

    /*批量保存市场活动*/
    int insertActivityByList(List<Activity> activityList);

    //根据id查询市场活动详情
    Activity selectActivityDetailById(String id);
    //查询线索明细页面关联的市场活动
    List<Activity> selectActivityForDetailByClueId(String clueId);

    //关联市场活动查询要关联的市场活动
    List<Activity> selectActivityForDetailByNameClueId(Map<String,Object> map);

    /*根据ids查询市场活动*/
    List<Activity> selectActivityForDetail(String[] ids);

    /*查询市场活动中已经被线索关联的市场活动*/
    List<Activity> selectActivityForConvertByNameClueId(Map<String,Object> map);


    /*导出勾选部分*/
    List<Activity> selectSomeActivityById(List<String> id);


}