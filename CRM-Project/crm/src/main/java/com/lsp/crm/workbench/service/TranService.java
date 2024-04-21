package com.lsp.crm.workbench.service;

import com.lsp.crm.workbench.pojo.Customer;
import com.lsp.crm.workbench.pojo.FunnelVO;
import com.lsp.crm.workbench.pojo.Tran;

import java.util.List;
import java.util.Map;

public interface TranService {
   void queryCustomerByName(Map<String,Object> map);
   Tran queryTranForDetailById(String id);

   List<FunnelVO> queryCountOfTranGroupByStage();
}
