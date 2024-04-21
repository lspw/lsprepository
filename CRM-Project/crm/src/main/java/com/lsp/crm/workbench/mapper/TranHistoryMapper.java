package com.lsp.crm.workbench.mapper;

import com.lsp.crm.workbench.pojo.TranHistory;
import com.lsp.crm.workbench.pojo.TranHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TranHistoryMapper {
    long countByExample(TranHistoryExample example);

    int deleteByExample(TranHistoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(TranHistory record);

    int insertSelective(TranHistory record);

    List<TranHistory> selectByExample(TranHistoryExample example);

    TranHistory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TranHistory record, @Param("example") TranHistoryExample example);

    int updateByExample(@Param("record") TranHistory record, @Param("example") TranHistoryExample example);

    int updateByPrimaryKeySelective(TranHistory record);

    int updateByPrimaryKey(TranHistory record);

    /*在创建交易向历史表中添加数据*/
    int insertTranHistory(TranHistory tranHistory);
    /*查询交易明细页面的历史记录*/
    List<TranHistory> selectTranHistoryForDetailByTranId(String tranId);
}