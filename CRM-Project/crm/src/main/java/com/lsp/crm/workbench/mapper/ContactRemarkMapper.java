package com.lsp.crm.workbench.mapper;

import com.lsp.crm.workbench.pojo.ContactRemark;
import com.lsp.crm.workbench.pojo.ContactRemarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContactRemarkMapper {
    long countByExample(ContactRemarkExample example);

    int deleteByExample(ContactRemarkExample example);

    int deleteByPrimaryKey(String id);

    int insert(ContactRemark record);

    int insertSelective(ContactRemark record);

    List<ContactRemark> selectByExample(ContactRemarkExample example);

    ContactRemark selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ContactRemark record, @Param("example") ContactRemarkExample example);

    int updateByExample(@Param("record") ContactRemark record, @Param("example") ContactRemarkExample example);

    int updateByPrimaryKeySelective(ContactRemark record);

    int updateByPrimaryKey(ContactRemark record);

    /*批量保存创建的联系人备注*/
    int insertContactRemarkByList(List<ContactRemark> list);
}