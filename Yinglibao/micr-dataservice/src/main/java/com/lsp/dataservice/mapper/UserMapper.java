package com.lsp.dataservice.mapper;

import com.lsp.ylb.api.model.UserAccount;
import com.lsp.ylb.api.pojo.User;
import com.lsp.ylb.api.pojo.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /*获取总的注册人数*/
    int selectCountUser();

    int updateByPrimaryKeySelective(User record);

    //根据手机号查询用户是否已经注册
    User selectUserByPhone(@Param("phone") String phone);

    //添加记录获取主键值
    int insertReturnPrimary(User user);

    User selectLoginByPhoneAndPwd(@Param("phone") String phone, @Param("loginPassword") String newPassword);

    /*更新数据库中实名认证的信息*/
    int updateRealName(@Param("name") String name,@Param("idCard") String idCard,@Param("phone") String phone);

    /*查询用户资金和信息*/
    UserAccount selectUserAccount(@Param("uid") Integer uid);

    User selectByPrimaryKey(@Param("id") Integer uid);

}