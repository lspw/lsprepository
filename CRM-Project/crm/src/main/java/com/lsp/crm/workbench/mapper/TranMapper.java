package com.lsp.crm.workbench.mapper;

import com.lsp.crm.workbench.pojo.FunnelVO;
import com.lsp.crm.workbench.pojo.Tran;
import com.lsp.crm.workbench.pojo.TranExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TranMapper {
    long countByExample(TranExample example);

    int deleteByExample(TranExample example);

    int deleteByPrimaryKey(String id);

    int insert(Tran record);

    int insertSelective(Tran record);

    List<Tran> selectByExample(TranExample example);

    Tran selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Tran record, @Param("example") TranExample example);

    int updateByExample(@Param("record") Tran record, @Param("example") TranExample example);

    int updateByPrimaryKeySelective(Tran record);

    int updateByPrimaryKey(Tran record);
/*保存创建的交易*/
    int insertTran(Tran tran);

    /*根据id查询交易明细*/
    Tran selectTranForDetailById(String id);
    /*查询交易表中各个阶段的数据*/
    List<FunnelVO> selectCountOfTranGroupByStage();


}