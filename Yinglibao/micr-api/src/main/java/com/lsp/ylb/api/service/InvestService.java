package com.lsp.ylb.api.service;

import com.lsp.ylb.api.model.BidListProduct;
import com.lsp.ylb.api.pojo.Bid;
import com.lsp.ylb.api.pojo.Income;

import java.math.BigDecimal;
import java.util.List;

public interface InvestService {





    /*查询产品的投资信息*/
   List<BidListProduct> queryBidListProductInvestById(Integer id,Integer pageNo,Integer pageSize);

    int queryUserInvest(Integer uid, Integer productId, BigDecimal money);

    //用户中心的‘最近投资’
    List<Bid> queryBidBy(Integer uid, Integer pageNo, Integer pageSize);
    //用户中心的‘最近收益’
    List<Income> queryRecentlyIncomeByUid(Integer uid, Integer pageNo, Integer pageSize);
}
