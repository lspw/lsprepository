package com.servlet.lsp.backstage.ht.service;

import com.servlet.lsp.backstage.ht.dao.GoodsDao;
import com.servlet.lsp.backstage.ht.dao.selectOrderDao;
import com.servlet.lsp.backstage.ht.pojo.Goods;
import com.servlet.lsp.backstage.ht.pojo.UserInfro;

import java.util.List;

public class BackStageService {
    GoodsDao goodsDao=new GoodsDao();
    selectOrderDao selectOrderDao=new selectOrderDao();
    public List<Goods> selectAllGoods(){
        List<Goods> goods = goodsDao.selectAll();
     return goods;
    }
    public  int addGoodsByAll(String goodsname,String goodsprice,String goodscategory, String goodshome,
    String goodsnum,String goodscount,String goodsimg){
         int count=goodsDao.addGoods(goodsname,goodsprice,goodscategory,goodshome,
                goodsnum,goodscount,goodsimg);
         return count;
    }
    public int deleteGoods(String goodsname,String goodsnum,String goodshome){
        int count = goodsDao.deleteGoodsByNameNumHome(goodsname, goodsnum, goodshome);
        return count;
    }
    public List<Goods> aboriginalInfro(String goodsname,String goodsnum,String goodshome){
        List<Goods> goods = goodsDao.aboriginalInfro(goodsname,goodsnum,goodshome);
        return goods;
    }
    public int modifyGoodsInfro(String goodsname,String goodsprice,String goodscategory, String goodshome,
                                String goodsnum,String goodscount,String goodsimg){
        int count = goodsDao.modifyGoodsInfro(goodsname, goodsprice, goodscategory, goodshome,
                goodsnum, goodscount, goodsimg);
        return count;
    }
    public List<UserInfro> selectOrder(){
        List<UserInfro> goods = selectOrderDao.selectOrder();
        return goods;
    }
}
