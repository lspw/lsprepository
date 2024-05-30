package com.lsp.web.view.invest;

public class RedisView {
    private String phoneNum;
    private Double score;

    public RedisView(String phoneNum, Double score) {
        this.phoneNum = phoneNum;
        this.score = score;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
