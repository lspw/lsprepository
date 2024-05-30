package com.lsp.dataservice.mapper;

import com.lsp.ylb.api.pojo.Product;
import com.lsp.ylb.api.pojo.ProductExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ProductMapper {
    long countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);


    /*计算收益总利率*/
    BigDecimal selectAvgRate();
    /*查询首页三种产品*/
    List<Product> selectByTypeLimit(@Param("ptype") Integer ptype,
                                    @Param("offset") Integer offset,
                                    @Param("rows") Integer rows);
    /*根据产品类型查询记录总数*/
    Integer selectCountByType(@Param("ptype") Integer ptype);

    //扣除产品的总资金
    int updateLeftProductMoney(@Param("productId") Integer productId,@Param("money") BigDecimal money);

    int updateSelled(@Param("id") Integer productId);

    List<Product> selectFullTimeProducts(@Param("beginTime") Date beginTime,@Param("endTime") Date endTime);

    int updateStatus(@Param("id") Integer id,@Param("newStatus") int newStatus);
}