package com.lsp.crm.workbench.mapper;

import com.lsp.crm.workbench.pojo.ContactActivityRelation;
import com.lsp.crm.workbench.pojo.ContactActivityRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContactActivityRelationMapper {
    long countByExample(ContactActivityRelationExample example);

    int deleteByExample(ContactActivityRelationExample example);

    int deleteByPrimaryKey(String id);

    int insert(ContactActivityRelation record);

    int insertSelective(ContactActivityRelation record);

    List<ContactActivityRelation> selectByExample(ContactActivityRelationExample example);

    ContactActivityRelation selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ContactActivityRelation record, @Param("example") ContactActivityRelationExample example);

    int updateByExample(@Param("record") ContactActivityRelation record, @Param("example") ContactActivityRelationExample example);

    int updateByPrimaryKeySelective(ContactActivityRelation record);

    int updateByPrimaryKey(ContactActivityRelation record);
/*批量保存创建的联系人和市场活动之间的关联关系*/
    int insertContactsActivityRelationByList(List<ContactActivityRelation> list);
}