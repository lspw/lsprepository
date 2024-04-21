package com.lsp.crm.workbench.service;

import com.lsp.crm.workbench.pojo.TranRemark;

import java.util.List;

public interface TranRemarkService {
    List<TranRemark> queryTranRemarkForDetailByTranId(String tranId);
}
