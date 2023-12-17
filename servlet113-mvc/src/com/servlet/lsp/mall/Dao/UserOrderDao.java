package com.servlet.lsp.mall.Dao;

import com.servlet.lsp.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//t_userorder
public class UserOrderDao {
    //添加用户信息
    public int addInfro(String username,String useraddress,String userphonenumber){
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into  t_userorder(username,useraddress,userphonenumber) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, useraddress);
            ps.setString(3, userphonenumber);
            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }
        return count;
    }


}
