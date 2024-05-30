package com.lsp.dataservice.mapper;

import com.lsp.ylb.api.pojo.Income;
import com.lsp.ylb.api.pojo.IncomeExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface IncomeMapper {
    long countByExample(IncomeExample example);

    int deleteByExample(IncomeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Income record);

    int insertSelective(Income record);

    List<Income> selectByExample(IncomeExample example);

    Income selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Income record, @Param("example") IncomeExample example);

    int updateByExample(@Param("record") Income record, @Param("example") IncomeExample example);

    int updateByPrimaryKeySelective(Income record);

    int updateByPrimaryKey(Income record);

    List<Income> selectExpiredIncome(@Param("expiredDate") Date expiredDate);

    List<Income> selectRecentlyIncomeByUid(@Param("uid") Integer uid,
                                           @Param("rows") Integer rows,
                                           @Param("offset") Integer offset);
}