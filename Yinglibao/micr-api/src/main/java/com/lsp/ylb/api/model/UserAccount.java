package com.lsp.ylb.api.model;

import com.lsp.ylb.api.pojo.User;

import java.math.BigDecimal;

public class UserAccount extends User {
    private BigDecimal availableMoney;

    public BigDecimal getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(BigDecimal availableMoney) {
        this.availableMoney = availableMoney;
    }
}
