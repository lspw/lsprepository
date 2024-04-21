package com.lsp.crm.workbench.mapper;

import com.lsp.crm.workbench.pojo.Customer;
import com.lsp.crm.workbench.pojo.CustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    long countByExample(CustomerExample example);

    int deleteByExample(CustomerExample example);

    int deleteByPrimaryKey(String id);

    int insert(Customer record);

    int insertSelective(Customer record);

    List<Customer> selectByExample(CustomerExample example);

    Customer selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);


    /*
    * 保存创建的客户
    * */
    int insertCustomer(Customer customer);
    /*
    * 模糊查询所有客户名称*/
    List<String> selectAllCustomerName(String customerName);

    Customer selectCustomerByName(String customerName);
}