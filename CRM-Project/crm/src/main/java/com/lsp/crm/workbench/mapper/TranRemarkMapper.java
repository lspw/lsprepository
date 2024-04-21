package com.lsp.crm.workbench.mapper;

import com.lsp.crm.workbench.pojo.TranRemark;
import com.lsp.crm.workbench.pojo.TranRemarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TranRemarkMapper {
    long countByExample(TranRemarkExample example);

    int deleteByExample(TranRemarkExample example);

    int deleteByPrimaryKey(String id);

    int insert(TranRemark record);

    int insertSelective(TranRemark record);

    List<TranRemark> selectByExample(TranRemarkExample example);

    TranRemark selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TranRemark record, @Param("example") TranRemarkExample example);

    int updateByExample(@Param("record") TranRemark record, @Param("example") TranRemarkExample example);

    int updateByPrimaryKeySelective(TranRemark record);

    int updateByPrimaryKey(TranRemark record);

    /*批量保存交易市场活动的备注*/
    int insertTranRemarkByList(List<TranRemark> list);
/*
* 查询介意页面的备注信息*/
    List<TranRemark> selectTranRemarkForDetailByTranId(String tranId);
}