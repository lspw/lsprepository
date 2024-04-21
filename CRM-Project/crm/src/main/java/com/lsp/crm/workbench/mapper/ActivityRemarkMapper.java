package com.lsp.crm.workbench.mapper;

import com.lsp.crm.workbench.pojo.ActivityRemark;
import com.lsp.crm.workbench.pojo.ActivityRemarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityRemarkMapper {
    long countByExample(ActivityRemarkExample example);

    int deleteByExample(ActivityRemarkExample example);

    int deleteByPrimaryKey(String id);

    int insert(ActivityRemark record);

    int insertSelective(ActivityRemark record);

    List<ActivityRemark> selectByExample(ActivityRemarkExample example);

    ActivityRemark selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ActivityRemark record, @Param("example") ActivityRemarkExample example);

    int updateByExample(@Param("record") ActivityRemark record, @Param("example") ActivityRemarkExample example);

    int updateByPrimaryKeySelective(ActivityRemark record);

    int updateByPrimaryKey(ActivityRemark record);
    //根据市场活动的id查询市场活动的备注
    List<ActivityRemark> selectActivityRemarkForDetailById(String id);
    //添加市场活动备注
    int insertActivityRemark(ActivityRemark remark);
    //删除市场活动备注
    int deleteActivityRemarkById(String id);
    //更新备注
    int updateActivityRemark(ActivityRemark remark);
}