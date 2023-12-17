package com.servlet.lsp.backstage.ht.dao;

import com.servlet.lsp.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//t_houtaiuser
public class UserDao {
//查询管理员信息
    public boolean selectuser(String username,String password){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        boolean success=false;
        try{
            conn= DBUtil.getConnection();
            String sql="select *from t_houtaiuser where username=? and password=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            rs=ps.executeQuery();
            if (rs.next()) {
                success = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return success;
    }
}
