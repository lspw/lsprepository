package com.lsp.dataservice.mapper;

import com.lsp.ylb.api.pojo.Recharger;
import com.lsp.ylb.api.pojo.RechargerExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RechargerMapper {
    long countByExample(RechargerExample example);

    int deleteByExample(RechargerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Recharger record);

    int insertSelective(Recharger record);

    List<Recharger> selectByExample(RechargerExample example);

    Recharger selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Recharger record, @Param("example") RechargerExample example);

    int updateByExample(@Param("record") Recharger record, @Param("example") RechargerExample example);

    int updateByPrimaryKeySelective(Recharger record);

    int updateByPrimaryKey(Recharger record);

   List<Recharger> selectByUid(@Param("uid") Integer uid,@Param("rows") Integer rows,@Param("offset") Integer offset);

    Recharger selectByRechargeNo(@Param("rechargerId") String orderId);

     int updateStatus(@Param("id") Integer id,@Param("newStatus") int rechargeStatusSuccess);
}