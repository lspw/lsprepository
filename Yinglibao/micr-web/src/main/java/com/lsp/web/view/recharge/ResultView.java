package com.lsp.web.view.recharge;

import com.lsp.ylb.api.pojo.Recharger;
import com.lsp.ylb.api.pojo.RechargerExample;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigDecimal;

public class ResultView {
    private Integer id;
    private String result="未知";
    private String rechargeDate="-";
    private BigDecimal rechargeMoney;

    public ResultView(Recharger record) {
        this.id=record.getId();
        this.rechargeMoney=record.getRechargeMoney();
        if (record.getRechargeTime()!=null){
            rechargeDate= DateFormatUtils.format(record.getRechargeTime(),"yyyy-MM-dd");
        }
        switch (record.getRechargeStatus()){
            case 0:
                result="充值中";
                break;
            case 1:
                result="成功";
                break;
            case 2:
                result="失败";
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(String rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(BigDecimal rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }
}
