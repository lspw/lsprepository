package com.servlet.lsp.mall.Dao;

import com.servlet.lsp.mall.pojo.Goods;

import com.servlet.lsp.mall.pojo.UserGoods;
import com.servlet.lsp.utils.DBUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//t_goodsmall
public class ShopGoodsMallDao {
    //模糊查询 实现搜索功能
    public List<Goods> searchByNum(String search){
        List<Goods> list=new ArrayList<>();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Goods goods=null;
        try {
            conn = DBUtil.getConnection();
            String sql="select *from t_goodsmall where goodsnum like  ?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,"%"+search+"%");
            rs=ps.executeQuery();
            while(rs.next()) {
                String goodsname = rs.getString("goodsname");
                double goodsprice = rs.getDouble("goodsprice");
                String goodscategory = rs.getString("goodscategory");
                String goodshome = rs.getString("goodshome");
                String goodsnum = rs.getString("goodsnum");
                String goodsimg = rs.getString("goodsimg");

                goods = new Goods();
                goods.setGoodsname(goodsname);
                goods.setGoodsprice(goodsprice);
                goods.setGoodscategory(goodscategory);
                goods.setGoodshome(goodshome);
                goods.setGoodsnum(goodsnum);
                goods.setGoodsimg(goodsimg);
                list.add(goods);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            DBUtil.close(conn,ps,rs);
        }
        return list;
    }
//查询所有货物
    public List<Goods> selectAll(){
        List<Goods> goodsList=new ArrayList<>();
        Goods goods=null;
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select *from t_goodsmall";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                String goodsname = rs.getString("goodsname");
                double goodsprice = rs.getDouble("goodsprice");
                String goodsimg = rs.getString("goodsimg");
                String goodsnum=rs.getString("goodsnum");
                int goodscount=rs.getInt("goodscount");
                //将数据传送到page1.jsp用于动态显示
                goods=new Goods();
                goods.setGoodsname(goodsname);
                goods.setGoodsprice(goodsprice);
                goods.setGoodsimg(goodsimg);
                goods.setGoodsnum(goodsnum);
                goods.setGoodscount(goodscount);
                goodsList.add(goods);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return goodsList;
    }
//查新单个商品信息
    public Goods selectOneByName(String goodsname1){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Goods goods1=null;
        try {
            //查询t_goodsmall将值传到details.jsp
            conn= DBUtil.getConnection();
            String sql="select *from t_goodsmall where goodsname=? ";
            ps=conn.prepareStatement(sql);
            ps.setString(1,goodsname1);
            rs=ps.executeQuery();
            while (rs.next()){
               int goodsid=rs.getInt("goodsid");//查出所有的id
               String goodsname = rs.getString("goodsname");
               double  goodsprice = rs.getDouble("goodsprice");
                String goodscategory = rs.getString("goodscategory");
                String goodshome = rs.getString("goodshome");
                String goodsnum = rs.getString("goodsnum");
                int  goodscount=rs.getInt("goodscount");
                String goodsimg=rs.getString("goodsimg");


                goods1=new Goods();
                goods1.setGoodsid(goodsid);
                goods1.setGoodsname(goodsname);
                goods1.setGoodsprice(goodsprice);
                goods1.setGoodscategory(goodscategory);
                goods1.setGoodshome(goodshome);
                goods1.setGoodsnum(goodsnum);
                goods1.setGoodscount(goodscount);
                goods1.setGoodsimg(goodsimg);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn,ps, rs);
        }
        return goods1;
    }
//查询商品库存
    public int selectByGoodsid(int goodsid ) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int goodscount=0;
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "select *from t_goodsmall where goodsid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, goodsid);
            rs = ps.executeQuery();
            if (rs.next()) {
                goodscount = rs.getInt("goodscount");
                if(goodscount!=0){
                    //更新商品数goodscount
                    String sql1 = "update t_goodsmall set goodscount=? where goodsid=? ";
                    ps = conn.prepareStatement(sql1);//编译sql语句
                    ps.setInt(1, goodscount - 1);
                    ps.setInt(2, goodsid);
                    int  count = ps.executeUpdate();
                }
            }
        } catch (SQLException  throwables) {
            throwables.printStackTrace();

        } finally {
            DBUtil.close(null, ps, rs);
        }
        return goodscount;
    }
public List<UserGoods> displayCar(int id,String username1){
    List<UserGoods> usergoods=new ArrayList<>();
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;

    try {
        conn= DBUtil.getConnection();
        String sql1="select goodsname ,goodsprice ,goodscategory,goodshome,goodsnum from t_goodsmall where goodsid=?";
        ps=conn.prepareStatement(sql1);
        ps.setInt(1,id);
        rs=ps.executeQuery();
        if (rs.next()) {
            String goodsname = rs.getString("goodsname");
            double goodsprice = rs.getDouble("goodsprice");
            String goodscategory = rs.getString("goodscategory");
            String goodshome = rs.getString("goodshome");
            String goodsnum = rs.getString("goodsnum");
            //将商品加入用户购买数据库（t_usershopgoods）
            String sql="insert into t_usershopgoods(username,goodsname,goodsprice,goodshome,goodsnum) values(?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,username1);
            ps.setString(2,goodsname);
            ps.setDouble(3,goodsprice);
            ps.setString(4,goodshome);
            ps.setString(5,goodsnum);
            int count1 = ps.executeUpdate();

            //根据用户查询用户已经加入购物车的商品 显示到页面（Pay.jsp）
            String sql2="select * from t_usershopgoods where  username=?";
            ps=conn.prepareStatement(sql2);
            /*  ps.setString(1,goodsname);*/
            ps.setString(1,username1);
            rs=ps.executeQuery();
            while (rs.next()) {
                  String name = rs.getString("goodsname");
                double price = rs.getDouble("goodsprice");
                String home = rs.getString("goodshome");
                String num = rs.getString("goodsnum");
                int goodsid_m = rs.getInt("goodsid_m");

                //将数据传输到Pay.jsp
               UserGoods  usergoods1=new UserGoods();
                usergoods1.setGoodsname(name);
                usergoods1.setGoodsprice(price);
                usergoods1.setGoodshome(home);
                usergoods1.setGoodsnum(num);
                usergoods1.setGoodsid_m(goodsid_m);
                usergoods.add(usergoods1);
            }
        }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }finally {
        DBUtil.close(conn,ps,rs);
    }
        return usergoods;
}
//
public List<UserGoods> delDisplayCar(int id,String username1){
    List<UserGoods> usergoods=new ArrayList<>();
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    try {
            conn=DBUtil.getConnection();
            //根据用户查询用户已经加入购物车的商品 显示到页面（Pay.jsp）
            String sql2="select * from t_usershopgoods where  username=?";
            ps=conn.prepareStatement(sql2);
            /*  ps.setString(1,goodsname);*/
            ps.setString(1,username1);
            rs=ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("goodsname");
                double price = rs.getDouble("goodsprice");
                String home = rs.getString("goodshome");
                String num = rs.getString("goodsnum");
                int goodsid_m = rs.getInt("goodsid_m");
                //将数据传输到Pay.jsp
                UserGoods  usergoods1=new UserGoods();
                usergoods1.setGoodsname(name);
                usergoods1.setGoodsprice(price);
                usergoods1.setGoodshome(home);
                usergoods1.setGoodsnum(num);
                usergoods1.setGoodsid_m(goodsid_m);
                usergoods.add(usergoods1);
            }

} catch (SQLException throwables) {
        throwables.printStackTrace();
    }finally {
        DBUtil.close(conn,ps,rs);
    }
    return usergoods;
}
}
