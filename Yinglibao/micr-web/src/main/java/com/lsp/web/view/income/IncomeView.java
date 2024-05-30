package com.lsp.web.view.income;

import com.lsp.web.controller.BaseController;
import com.lsp.ylb.api.pojo.Income;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigDecimal;

public class IncomeView {
    private Integer uid;
    private Integer loanId;
    private String incomeDate="-";
    private BigDecimal incomeMoney;
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public String getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(String incomeDate) {
        this.incomeDate = incomeDate;
    }

    public BigDecimal getIncomeMoney() {
        return incomeMoney;
    }

    public void setIncomeMoney(BigDecimal incomeMoney) {
        this.incomeMoney = incomeMoney;
    }




    public IncomeView(Income income) {
        this.uid = income.getUid();
        this.loanId =income.getLoanId();

        this.incomeMoney = income.getIncomeMoney();
        if (income.getIncomeDate()!=null){
            incomeDate = DateFormatUtils.format(income.getIncomeDate(),"yyyy-MM-dd");
        }

    }
}
