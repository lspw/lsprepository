package com.lsp.dataservice.service;

import com.lsp.commons.utils.CommonUtil;
import com.lsp.dataservice.mapper.FinanceMapper;
import com.lsp.dataservice.mapper.UserMapper;
import com.lsp.ylb.api.model.UserAccount;
import com.lsp.ylb.api.pojo.Finance;
import com.lsp.ylb.api.pojo.User;
import com.lsp.ylb.api.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@DubboService(interfaceClass = UserService.class,version = "1.0-SNAPSHOT")

public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Value("${ylb.config.password-salt}")
    private String passwordSalt;

    @Resource
    private FinanceMapper financeMapper;

    @Override
    public User queryUserByPhone(String phone) {
        //判断手机号
        User user=new User();
        if (CommonUtil.checkPhone(phone)) {
            user=userMapper.selectUserByPhone(phone);
        }
        return user;
    }

    /*注册用户*/
    //向数据库中添加数据需要事物
    @Transactional(rollbackFor = Exception.class)
    @Override
    public synchronized int userRegister(String phone, String password) {
        int result = 0;//默认参数不正确
        //验证数据格式
        if (CommonUtil.checkPhone(phone) && (password.length()==32 && password!=null)){
            //格式正确 注册u_user
            // 对原始密码进行二次机密（加盐（salt））

            //判断手机号是否在库中
            if (userMapper.selectUserByPhone(phone)==null){
                //不存在库中可以注册
                String newPassWord = DigestUtils.md5Hex(password+passwordSalt);
                User user=new User();
                user.setPhone(phone);
                user.setAddTime(new Date());
                user.setLoginPassword(newPassWord);//对密码进行二次加密加加盐
                userMapper.insertReturnPrimary(user);
                //将注册的用户添加到u_finance_account表中
                Finance account=new Finance();
                //uid是获取user表中的id
                account.setUid(user.getId());
                account.setAvailableMoney(new BigDecimal("0"));
                financeMapper.insertSelective(account);
                //添加成功
                result=1;
            }else{
                //手机号存在 不能注册 请求参数有误
                result=2;
            }
        }
        return result;
    }



    //登陆查新
    @Transactional(rollbackFor = Exception.class)
    @Override
    public User queryLoginUser(String phone, String password) {

        User user=null;
        //数据验证
        if (CommonUtil.checkPhone(phone) &&( password!=null&&password.length()==32 )){
            //userMapper中查询数据
            //加盐
            String newPassWord = DigestUtils.md5Hex( password+passwordSalt);
            user=userMapper.selectLoginByPhoneAndPwd(phone,newPassWord);
            //更新最后得到登录时间表
            if (user!=null){
                user.setLastLoginTime(new Date());
                userMapper.updateByPrimaryKeySelective(user);
            }

        }else {

        }
        return user;
    }

    /*更新数据库实名认证的信息*/
    @Override
    public boolean modifyRealName(String name, String idCard, String phone) {
        //参数判断isAnyBlank()有一个元素为空回true
        int resultRealNameRows=0;
        if (!StringUtils.isAnyBlank(name,phone,idCard)){

         resultRealNameRows= userMapper.updateRealName(name,idCard,phone);
        }
        return resultRealNameRows>0;
    }

    @Override
    public UserAccount queryUserAccount(Integer uid) {
        UserAccount userAccount=null;
        if (uid!=null && uid>0){
           userAccount = userMapper.selectUserAccount(uid);
        }

        return userAccount;
    }

    @Override
    public User queryById(Integer uid) {
        User user=null;
        if (uid!=null && uid>0){
            user=userMapper.selectByPrimaryKey(uid);
        }
        return user;


    }
}
