package com.lsp.crm.settings.mapper;

import com.lsp.crm.settings.pojo.User;
import com.lsp.crm.settings.pojo.UserExample;
import java.util.List;
import java.util.Map;

import com.lsp.crm.workbench.pojo.Activity;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByLoginActAndPwd(Map<String,Object> map);

    //查询所有用户
    List<User> selectAllUser();


}