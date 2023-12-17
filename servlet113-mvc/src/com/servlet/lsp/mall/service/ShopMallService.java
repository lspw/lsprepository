package com.servlet.lsp.mall.service;

import com.servlet.lsp.mall.Dao.ShopGoodsMallDao;
import com.servlet.lsp.mall.Dao.ShopUserMallDao;
import com.servlet.lsp.mall.Dao.UserOrderDao;
import com.servlet.lsp.mall.Dao.UserShopGoodsDao;
import com.servlet.lsp.mall.pojo.Goods;
import com.servlet.lsp.mall.pojo.UserGoods;

import java.util.List;

public class ShopMallService {
    ShopUserMallDao shopUserMallDao=new ShopUserMallDao();
    ShopGoodsMallDao shopGoodsMallDao=new ShopGoodsMallDao();
    UserShopGoodsDao userShopGoodsDao=new UserShopGoodsDao();
    UserOrderDao userOrderDao=new UserOrderDao();
    public boolean selectUser(String username,String password){
        boolean success = shopUserMallDao.selectuserInfroByNP(username, password);
        return success;
    }
    public List<Goods> searchGoods(String search){
        List<Goods> list = shopGoodsMallDao.searchByNum(search);
        return list;
    }
    public List<Goods> selectAll(){
        List<Goods> goodsList= shopGoodsMallDao.selectAll();
        return goodsList;
    }
    public Goods selectOne(String goodsname){
        Goods goods1 = shopGoodsMallDao.selectOneByName(goodsname);
        return goods1;
    }
    public double sum(String username){
        double sumgoodsprice = userShopGoodsDao.selectSumByUserName(username);
        return sumgoodsprice;
    }
    public int selectGoodsInventory(int goodsid){
        int goodscount = shopGoodsMallDao.selectByGoodsid(goodsid);
        return goodscount;
    }
public List<UserGoods> displayCar(int id,String username1){
    List<UserGoods> usergoods = shopGoodsMallDao.displayCar(id, username1);
    return usergoods;
}
public UserGoods deleteGoodsByid(int goodsid_m,String goodsname ){
    UserGoods usergoods = userShopGoodsDao.deleteGoodsByid(goodsid_m, goodsname);
    return usergoods;
}
    public List<UserGoods>  delDisplayCar(int id,String username1) {
        List<UserGoods> usergoods = shopGoodsMallDao.delDisplayCar(id, username1);
        return usergoods;
    }
   public int  UserOrderDao(String username,String useraddress,String userphonenumber){
       int count = userOrderDao.addInfro(username,useraddress,userphonenumber);
       return count;
   }
}
