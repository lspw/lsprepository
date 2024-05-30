package com.lsp.dataservice.mapper;

import com.lsp.ylb.api.model.BidListProduct;
import com.lsp.ylb.api.pojo.Bid;
import com.lsp.ylb.api.pojo.BidExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BidMapper {

    long countByExample(BidExample example);

    int deleteByExample(BidExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Bid record);

    int insertSelective(Bid record);

    List<Bid> selectByExample(BidExample example);

    Bid selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Bid record, @Param("example") BidExample example);

    int updateByExample(@Param("record") Bid record, @Param("example") BidExample example);

    int updateByPrimaryKeySelective(Bid record);

    int updateByPrimaryKey(Bid record);


    /*查询累计交易额*/
    BigDecimal selectSumMoney();
    /*根据id分页查询产品投资信息*/
    List<BidListProduct> selectByProductById(@Param("id") Integer id,
                                             @Param("offset") int offset,
                                             @Param("rows") Integer rows);

    List<Bid> selectByProdId(@Param("productId") Integer productId);

    List<Bid> selectBidByUid(@Param("uid") Integer uid,@Param("rows") Integer rows,@Param("offset") int offset);
}