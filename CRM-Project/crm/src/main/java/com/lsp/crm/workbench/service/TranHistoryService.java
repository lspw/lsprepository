package com.lsp.crm.workbench.service;

import com.lsp.crm.workbench.pojo.TranHistory;

import java.util.List;

public interface TranHistoryService {
    List<TranHistory> queryTranHistoryForDetailByTranId(String tranId);
}
