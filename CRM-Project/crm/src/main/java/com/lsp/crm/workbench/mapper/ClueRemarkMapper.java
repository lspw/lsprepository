package com.lsp.crm.workbench.mapper;

import com.lsp.crm.workbench.pojo.ClueRemark;
import com.lsp.crm.workbench.pojo.ClueRemarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClueRemarkMapper {
    long countByExample(ClueRemarkExample example);

    int deleteByExample(ClueRemarkExample example);

    int deleteByPrimaryKey(String id);

    int insert(ClueRemark record);

    int insertSelective(ClueRemark record);

    List<ClueRemark> selectByExample(ClueRemarkExample example);

    ClueRemark selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ClueRemark record, @Param("example") ClueRemarkExample example);

    int updateByExample(@Param("record") ClueRemark record, @Param("example") ClueRemarkExample example);

    int updateByPrimaryKeySelective(ClueRemark record);

    int updateByPrimaryKey(ClueRemark record);

    //查询线索备注信息
    List<ClueRemark> selectClueRemarkForDetailByClueId(String clueId);


    List<ClueRemark> selectClueRemarkByClueId(String clueId);

    /*根据clueid删除线索的备注*/
    int deleteClueRemarkByClueId(String clueId);


}