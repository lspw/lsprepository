package com.lsp.ylb.api.service;

import com.lsp.ylb.api.pojo.Recharger;

import java.util.List;

public interface RechargeService {

    List<Recharger> queryByUid(Integer uid,Integer pageNo,Integer pageSize);

    int addRechargeRecord(Recharger recharger);

    /*处理异步请求*/
    int handleKQNotify(String orderId, String payAmount, String payResult);
}
