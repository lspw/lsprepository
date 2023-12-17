package com.servlet.lsp.backstage.ht.dao;

import com.servlet.lsp.backstage.ht.pojo.Goods;
import com.servlet.lsp.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao {
    //查询所有商品
    public List<Goods> selectAll(){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Goods> goods=new ArrayList<>();
        try{
            conn= DBUtil.getConnection();
            String sql="select goodsid,goodsname,goodsprice,goodscategory,goodshome,goodsnum,goodscount,goodsimg from t_goodsmall";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            Goods goods1=null;
            while (rs.next()){
                int goodsid=rs.getInt("goodsid");
                String goodsname = rs.getString("goodsname");
                double goodsprice = rs.getDouble("goodsprice");
                String goodscategory = rs.getString("goodscategory");
                String goodshome = rs.getString("goodshome");
                String goodsnum = rs.getString("goodsnum");
                int goodscount = rs.getInt("goodscount");
                String goodsimg = rs.getString("goodsimg");

                goods1=new Goods();
                goods1.setGoodsid(goodsid);
                goods1.setGoodsname(goodsname);
                goods1.setGoodsprice(goodsprice);
                goods1.setGoodscategory(goodscategory);
                goods1.setGoodshome(goodshome);
                goods1.setGoodsnum(goodsnum);
                goods1.setGoodscount(goodscount);
                goods1.setGoodsimg(goodsimg);
                goods.add(goods1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn,ps, rs);
        }
        return goods;
    }
 //添加商品
public  int addGoods(String goodsname,String goodsprice,String goodscategory, String goodshome,
                       String goodsnum,String goodscount,String goodsimg) {
     Connection conn=null;
     PreparedStatement ps=null;
     int count=0;
     try {
         conn=DBUtil.getConnection();
         String sql="insert into t_goodsmall(goodsname,goodsprice,goodscategory,goodshome,goodsnum, goodscount,goodsimg) values(?,?,?,?,?,?,?)";
         ps=conn.prepareStatement(sql);
         ps.setString(1,goodsname);
         ps.setString(2,goodsprice);
         ps.setString(3,goodscategory);
         ps.setString(4,goodshome);
         ps.setString(5,goodsnum);
         ps.setString(6,goodscount);
         ps.setString(7,goodsimg);
         count = ps.executeUpdate();
     } catch (SQLException throwables) {
         throwables.printStackTrace();
     }finally {
         DBUtil.close(conn,ps,null);
     }
     return count;
 }
//删除商品（goodsname,goodsnum,goodshome）
    public int deleteGoodsByNameNumHome(String goodsname,String goodsnum,String goodshome){
        Connection conn=null;
        PreparedStatement ps=null;
        int count=1;
        try {
            conn= DBUtil.getConnection();
            String sql="delete from t_goodsmall where goodsname=? and goodshome=? and goodsnum=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,goodsname);
            ps.setString(2,goodshome);
            ps.setString(3,goodsnum);
            count=ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,null);
        }
        return count;
    }
//修改商品信息
//1.(显示商品的原有信息)
public List<Goods> aboriginalInfro(String goodsname,String goodsnum,String goodshome){

    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    List<Goods> goods=new  ArrayList<>();
    try {
        conn=DBUtil.getConnection();
        String sql="select goodsname,goodsprice,goodscategory,goodshome,goodsnum,goodscount,goodsimg from t_goodsmall where  goodsname=? and goodshome=? and goodsnum=?";
        ps=conn.prepareStatement(sql);
        ps.setString(1,goodsname);
        ps.setString(2,goodshome);
        ps.setString(3,goodsnum);
        rs=ps.executeQuery();
        Goods goods1 = null;
        if(rs.next()){
            goodsname= rs.getString("goodsname");
            double goodsprice = rs.getDouble("goodsprice");
            String goodscategory = rs.getString("goodscategory");
            goodshome = rs.getString("goodshome");
            goodsnum = rs.getString("goodsnum");
            int goodscount = rs.getInt("goodscount");
            String goodsimg = rs.getString("goodsimg");

            goods1=new Goods();
            goods1.setGoodsname(goodsname);
            goods1.setGoodsprice(goodsprice);
            goods1.setGoodscategory(goodscategory);
            goods1.setGoodshome(goodshome);
            goods1.setGoodsnum(goodsnum);
            goods1.setGoodscount(goodscount);
            goods1.setGoodsimg(goodsimg);
            goods.add(goods1);
        }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }finally{
        DBUtil.close(conn,ps,rs);
    }
     return  goods;
}
//修改商品信息
//2.(修改商品的原有信息)
public int modifyGoodsInfro(String goodsname,String goodsprice,String goodscategory, String goodshome,
                            String goodsnum,String goodscount,String goodsimg){

    Connection conn=null;
    PreparedStatement ps=null;
    int count=0;
    try {
        conn=DBUtil.getConnection();
        String sql="update t_goodsmall set goodsprice=?,goodscategory=?,goodscount=?,goodsimg=? where goodsname=? and goodshome=? and goodsnum=?";
        ps=conn.prepareStatement(sql);//编译sql语句
        ps.setString(1,goodsprice);
        ps.setString(2,goodscategory);
        ps.setString(3,goodscount);
        ps.setString(4,goodsimg);
        ps.setString(5,goodsname);
        ps.setString(6,goodshome);
        ps.setString(7,goodsnum);
        ps.executeUpdate();
        count = ps.executeUpdate();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }finally {
        DBUtil.close(conn,ps,null);
    }
        return count;
}

}
