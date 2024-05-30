package com.lsp.ylb.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

/*因为要将数据传输给数据层所以要序列化*/
public class BaseInfo implements Serializable {
    /*收益率平均值*/
    private BigDecimal historyAvgRate;
    /*累计成交金额*/
    private BigDecimal sumBidMoney;

    /*注册人数*/
    private Integer registerUsers;

    public BaseInfo() {
    }

    public BigDecimal getHistoryAvgRate() {
        return historyAvgRate;
    }

    public void setHistoryAvgRate(BigDecimal historyAvgRate) {
        this.historyAvgRate = historyAvgRate;
    }

    public BigDecimal getSumBidMoney() {
        return sumBidMoney;
    }

    public void setSumBidMoney(BigDecimal sumBidMoney) {
        this.sumBidMoney = sumBidMoney;
    }

    public Integer getRegisterUsers() {
        return registerUsers;
    }

    public void setRegisterUsers(Integer registerUsers) {
        this.registerUsers = registerUsers;
    }

    public BaseInfo(BigDecimal historyAvgRate, BigDecimal sumBidMoney, Integer registerUsers) {
        this.historyAvgRate = historyAvgRate;
        this.sumBidMoney = sumBidMoney;
        this.registerUsers = registerUsers;
    }
}
