package com.servlet.lsp.mall.pojo;

public class Goods {
    private String goodsname;
    private double goodsprice;
    private String goodscategory;//类别
    private String goodshome;//生产厂家
    private String goodsnum;//版本号
    private int goodscount;
    private int goodsid;
    private String goodsimg;
    @Override
    public String toString() {
        return "Goods{" +
                "goodsname='" + goodsname + '\'' +
                ", goodsprice=" + goodsprice +
                ", goodscategory='" + goodscategory + '\'' +
                ", goodshome='" + goodshome + '\'' +
                ", goodsnum='" + goodsnum + '\'' +
                ", goodsid=" + goodsid +
                ", goodscount=" + goodscount +
                ", goodsimg='" + goodsimg + '\'' +
                '}';
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

    public String getGoodscategory() {
        return goodscategory;
    }

    public void setGoodscategory(String goodscategory) {
        this.goodscategory = goodscategory;
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

    public int getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(int goodsid) {
        this.goodsid = goodsid;
    }

    public int getGoodscount() {
        return goodscount;
    }

    public void setGoodscount(int goodscount) {
        this.goodscount = goodscount;
    }

    public String getGoodsimg() {
        return goodsimg;
    }

    public void setGoodsimg(String goodsimg) {
        this.goodsimg = goodsimg;
    }

    public Goods(String goodsname, double goodsprice, String goodscategory, String goodshome, String goodsnum, int goodsid, int goodscount, String goodsimg) {
        this.goodsname = goodsname;
        this.goodsprice = goodsprice;
        this.goodscategory = goodscategory;
        this.goodshome = goodshome;
        this.goodsnum = goodsnum;
        this.goodsid = goodsid;
        this.goodscount = goodscount;
        this.goodsimg = goodsimg;
    }

    public Goods() {
    }
}
