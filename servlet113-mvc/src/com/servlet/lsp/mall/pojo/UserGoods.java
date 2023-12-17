package com.servlet.lsp.mall.pojo;

public class UserGoods {
    String username1;
    private String goodsname;
    private double goodsprice;
    private String goodshome;
    private String goodsnum;
    private int goodsid_m;

    @Override
    public String toString() {
        return "UserGoods{" +
                "username1='" + username1 + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", goodsprice=" + goodsprice +
                ", goodshome='" + goodshome + '\'' +
                ", goodsnum='" + goodsnum + '\'' +
                ", goodsid_m=" + goodsid_m +
                '}';
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public double getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(double goodsprice) {
        this.goodsprice = goodsprice;
    }

    public String getGoodshome() {
        return goodshome;
    }

    public void setGoodshome(String goodshome) {
        this.goodshome = goodshome;
    }

    public String getGoodsnum() {
        return goodsnum;
    }

    public void setGoodsnum(String goodsnum) {
        this.goodsnum = goodsnum;
    }

    public int getGoodsid_m() {
        return goodsid_m;
    }

    public void setGoodsid_m(int goodsid_m) {
        this.goodsid_m = goodsid_m;
    }

    public UserGoods(String username1, String goodsname, double goodsprice, String goodshome, String goodsnum, int goodsid_m) {
        this.username1 = username1;
        this.goodsname = goodsname;
        this.goodsprice = goodsprice;
        this.goodshome = goodshome;
        this.goodsnum = goodsnum;
        this.goodsid_m = goodsid_m;
    }

    public UserGoods() {
    }
}
