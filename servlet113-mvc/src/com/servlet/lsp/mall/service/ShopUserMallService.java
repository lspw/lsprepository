package com.servlet.lsp.mall.service;

import com.servlet.lsp.mall.Dao.ShopUserMallDao;

public class ShopUserMallService {
    ShopUserMallDao shopUserMallDao=new ShopUserMallDao();
    public boolean Logon(String username,String password){
        boolean success = shopUserMallDao.selectuserInfroByNP(username, password);
        return success;
    }
    public int login(String username,String password){
        int count = shopUserMallDao.addUserInfro(username, password);
        return count;
    }
}
