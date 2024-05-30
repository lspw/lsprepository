package com.lsp.web.view.bid;

import com.lsp.ylb.api.pojo.Bid;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigDecimal;
import java.util.Date;

public class BidView {
    private Integer id;
    private Integer loanId;
    private BigDecimal bidMoney;
    private String bidTime="-";

    public BidView(Bid bid) {
        this.id =bid.getId();
        this.loanId = bid.getLoanId();
        this.bidMoney = bid.getBidMoney();
        if (bid.getBidTime()!=null){
            bidTime= DateFormatUtils.format(bid.getBidTime(),"yyyy-MM-dd");
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public BigDecimal getBidMoney() {
        return bidMoney;
    }

    public void setBidMoney(BigDecimal bidMoney) {
        this.bidMoney = bidMoney;
    }

    public String getBidTime() {
        return bidTime;
    }

    public void setBidTime(String bidTime) {
        this.bidTime = bidTime;
    }
}
