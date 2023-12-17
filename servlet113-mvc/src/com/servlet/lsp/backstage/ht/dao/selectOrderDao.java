package com.servlet.lsp.backstage.ht.dao;

import com.servlet.lsp.backstage.ht.pojo.Goods;
import com.servlet.lsp.backstage.ht.pojo.User;
import com.servlet.lsp.backstage.ht.pojo.UserInfro;
import com.servlet.lsp.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//t_userorder
public class selectOrderDao {
    //查询所有商品
    public List<UserInfro> selectOrder(){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<UserInfro> goods=new ArrayList<>();
        try{
            conn= DBUtil.getConnection();
            String sql="select username,useraddress,userphonenumber from t_userorder";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            UserInfro goods1=null;
            while (rs.next()){
                String username = rs.getString("username");
                String useraddress = rs.getString("useraddress");
                String userphonenumber = rs.getString("userphonenumber");
                goods1=new UserInfro();
                goods1.setUsername(username);
                goods1.setUseraddress(useraddress );
                goods1.setUserphonenumber(userphonenumber);
                goods.add(goods1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn,ps, rs);
        }
        return goods;
    }

}
