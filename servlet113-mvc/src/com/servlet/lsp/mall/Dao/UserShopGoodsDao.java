package com.servlet.lsp.mall.Dao;

import com.servlet.lsp.mall.pojo.UserGoods;
import com.servlet.lsp.utils.DBUtil;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//t_t_usershopgoods
public class UserShopGoodsDao {
    //结算查看商品价格总和
    public double selectSumByUserName(String username1){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        double sumgoodsprice = 0;
        try {
            conn= DBUtil.getConnection();
            String sql="select sum(goodsprice) from t_usershopgoods where username=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,username1);
            rs=ps.executeQuery();
            if(rs.next()){
                sumgoodsprice = rs.getDouble("sum(goodsprice)");
                int goodsid_m = rs.getInt("goodsid_m");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }

        return sumgoodsprice;
    }

    public UserGoods deleteGoodsByid(int goodsid_m,String goodsname ){

        UserGoods usergoods=new UserGoods();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int count;
        double sumgoodsprice = 0;
        try {
            conn=DBUtil.getConnection();
            String sql="delete from t_usershopgoods where goodsname=? and goodsid_m=? ";
            ps=conn.prepareStatement(sql);
            ps.setString(1,goodsname);
            ps.setInt(2,goodsid_m);
            count=ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,null);
        }
        return usergoods;
    }

}
