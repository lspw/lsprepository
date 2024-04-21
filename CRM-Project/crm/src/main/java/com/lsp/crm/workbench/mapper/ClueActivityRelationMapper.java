package com.lsp.crm.workbench.mapper;

import com.lsp.crm.workbench.pojo.ClueActivityRelation;
import com.lsp.crm.workbench.pojo.ClueActivityRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClueActivityRelationMapper {
    long countByExample(ClueActivityRelationExample example);

    int deleteByExample(ClueActivityRelationExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClueActivityRelation record);

    int insertSelective(ClueActivityRelation record);

    List<ClueActivityRelation> selectByExample(ClueActivityRelationExample example);

    ClueActivityRelation selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClueActivityRelation record, @Param("example") ClueActivityRelationExample example);

    int updateByExample(@Param("record") ClueActivityRelation record, @Param("example") ClueActivityRelationExample example);

    int updateByPrimaryKeySelective(ClueActivityRelation record);

    int updateByPrimaryKey(ClueActivityRelation record);

    /*批量保存创建的线索和市场活动的关联关系*/
    int insertClueActivityRelationByList(List<ClueActivityRelation> list);

    /*解除市场活动*/
    int deleteClueActivityRelationByClueIdActivityId(ClueActivityRelation relation);
    //根据ClueId查询市场活动和线索的关联关系
    List<ClueActivityRelation> selectClueActivityRelationByClueId(String clueId);
/*根据clueId删除市场活动和线索关联关系*/
    int deleteClueActivityRelationByClueId(String clueId);

}