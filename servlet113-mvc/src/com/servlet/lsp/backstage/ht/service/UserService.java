package com.servlet.lsp.backstage.ht.service;

import com.servlet.lsp.backstage.ht.dao.UserDao;

public class UserService {
    UserDao userDao=new UserDao();
    public boolean logon(String username,String password){
        boolean success = userDao.selectuser(username, password);
        return success;
    }
}
