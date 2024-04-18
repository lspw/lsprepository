package com.lsp.crm.settings.service;

import com.lsp.crm.settings.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User queryUserByLoginActAndPwd(Map<String,Object> map);

    List<User> queryAllUser();
}
