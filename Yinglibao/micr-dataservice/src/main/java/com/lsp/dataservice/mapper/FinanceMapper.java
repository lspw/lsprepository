package com.lsp.dataservice.mapper;

import com.lsp.ylb.api.pojo.Finance;
import com.lsp.ylb.api.pojo.FinanceExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface FinanceMapper {
    long countByExample(FinanceExample example);

    int deleteByExample(FinanceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Finance record);

    int insertSelective(Finance record);

    List<Finance> selectByExample(FinanceExample example);

    Finance selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Finance record, @Param("example") FinanceExample example);

    int updateByExample(@Param("record") Finance record, @Param("example") FinanceExample example);

    int updateByPrimaryKeySelective(Finance record);

    int updateByPrimaryKey(Finance record);
    /*检查账号金额*/
    Finance selectByUidForUpdate(@Param("uid") Integer uid);

    int updateAvailableMoneyByInvest(@Param("uid") Integer uid,@Param("money") BigDecimal money);
    //收益返还更新资金
    void upateAvailableMoneyByIncomeBack(@Param("id") Integer uid,
                                         @Param("bidMoney") BigDecimal bidMoney,
                                         @Param("incomeMoney") BigDecimal incomeMoney);

    //充值更新金额
    int updateAvailableMoneyByRecharge(@Param("uid") Integer uid,@Param("rechargeMoney") BigDecimal rechargeMoney);
}