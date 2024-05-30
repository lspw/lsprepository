package com.lsp.ylb.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BidListProduct implements Serializable {
    private Integer id;
    private String phone;
    private String time;
    private BigDecimal BidMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BigDecimal getBidMoney() {
        return BidMoney;
    }

    public void setBidMoney(BigDecimal bidMoney) {
        BidMoney = bidMoney;
    }
}
