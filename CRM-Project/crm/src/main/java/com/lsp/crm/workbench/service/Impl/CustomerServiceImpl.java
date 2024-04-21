package com.lsp.crm.workbench.service.Impl;

import com.lsp.crm.workbench.mapper.CustomerMapper;
import com.lsp.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public List<String> queryAllCustomerName(String customerName) {
       return customerMapper.selectAllCustomerName(customerName);
    }
}
