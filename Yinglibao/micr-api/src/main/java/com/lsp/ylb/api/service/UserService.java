package com.lsp.ylb.api.service;

import com.lsp.ylb.api.model.UserAccount;
import com.lsp.ylb.api.pojo.User;

public interface UserService {
    User queryLoginUser(String phone, String pword);

    User queryUserByPhone(String phone);

    int userRegister(String phone, String password);

    boolean modifyRealName(String name, String idCard, String phone);

    /*查询用户信息和资金*/
    UserAccount queryUserAccount(Integer uid);

    User queryById(Integer uid);
}
