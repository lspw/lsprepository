package com.servlet.lsp.mall.web;

import com.servlet.lsp.mall.exception.GoodsNotEnoughException;
import com.servlet.lsp.mall.pojo.Goods;
import com.servlet.lsp.mall.pojo.User;
import com.servlet.lsp.mall.pojo.UserGoods;
import com.servlet.lsp.mall.service.ShopMallService;
import com.servlet.lsp.mall.service.ShopUserMallService;
import com.servlet.lsp.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/welcome","/search","/page2","/details","/pay","/inmall","/delete","/order"})
public class ShopMallServlet extends HttpServlet {
    ShopMallService shopMallService=new ShopMallService();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ShopUserMallService shopUserMallService=new ShopUserMallService();
            String servletPath = request.getServletPath();
            if ("/welcome".equals(servletPath)) {
                doWelcome(request,response);
            } else if ("/search".equals(servletPath)) {
                doSearch(request,response);
            } else if ("/page2".equals(servletPath)) {
                doShow(request,response);
            }else if ("/details".equals(servletPath)) {
                doDetails(request,response);
            }else if ("/pay".equals(servletPath)) {
                doPay(request,response);
            }
            else if("/inmall".equals(servletPath)){
                try {
                    doAddGoods(request, response);
                } catch (GoodsNotEnoughException e) {
                    response.sendRedirect(request.getContextPath()+"/goodsnotenough.jsp");
                }
            }else if ("/delete".equals(servletPath)) {
                doDel(request,response);
            }else if ("/order".equals(servletPath)) {
                doOrder(request,response);
            }
    }

    private void doOrder(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String phonenumber = request.getParameter("phonenumber");
        String goodsname = request.getParameter("goodsname");
        int count= shopMallService.UserOrderDao(username,address, phonenumber);
        double sumgoodsprice = shopMallService.sum(username);
        request.setAttribute("sumgoodsprice",sumgoodsprice);
        request.getRequestDispatcher("/Pay02.jsp").forward(request,response);

    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String goodsid_m = request.getParameter("goodsid_m");
        int goodsid_m1= Integer.parseInt(goodsid_m);
        String goodsname = request.getParameter("goodsname");
        UserGoods usergoods1 = shopMallService.deleteGoodsByid(goodsid_m1, goodsname);
        List<UserGoods> usergoods = shopMallService.delDisplayCar(id, username);
        request.setAttribute("usergoods",usergoods);
        request.getRequestDispatcher("/Pay.jsp").forward(request,response);

    }
    String username=null;
    int id=0;
    private void doAddGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, GoodsNotEnoughException {
        //判断是否登录
        //获取cookie
        Cookie[] cookies = request.getCookies();
        username=null;
        String password=null;
        //查询cookie中是否有对应的 username 和 password
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if("username".equals(name)){
                    username = cookie.getValue();
                }else if("password".equals(name)){
                    password = cookie.getValue();
                }
            }
        }
        if(username!=null&&password!=null){//判断用户名和密码是否为空
            //判断用户名和密码是否正确
            boolean success = shopMallService.selectUser(username, password);
            //已登录
            if (success) {
                //获取session
                HttpSession session=request.getSession();
                session.setAttribute("username",username);
                User user=new User(username, password);
                session.setAttribute("user",user);
                String goodsid = request.getParameter("goodsid");
                id=Integer.parseInt(goodsid);
                //查询商品库存（通过goodsid）
                int goodscount= shopMallService.selectGoodsInventory(id);//获取商品数goodscount
                //有货加入购物车
                if(goodscount<=0){
                    throw new GoodsNotEnoughException("缺货");
                }
                List<UserGoods> usergoods = shopMallService.displayCar(id, username);
                request.setAttribute("usergoods",usergoods);
                request.setAttribute("username",username);
                request.getRequestDispatcher("/Pay.jsp").forward(request,response);
            }else {
                response.sendRedirect(request.getContextPath()+"/Logon.jsp");
            }
        }else {
            //不正确
            response.sendRedirect(request.getContextPath()+"/Logon.jsp");
        }
    }
    double sumgoodsprice=0;
  private void doPay(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         double sumgoodsprice = shopMallService.sum(username);
         request.setAttribute("sumgoodsprice",sumgoodsprice);
         request.setAttribute("username",username);
         request.getRequestDispatcher("/userinfor.jsp").forward(request,response);
     }
    String goodsname1=null;
    private void doDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String goodsname1 = request.getParameter("goodsname");
        Goods goods1 = shopMallService.selectOne(goodsname1);
        request.setAttribute("goods",goods1);
        request.getRequestDispatcher("/details.jsp").forward(request,response);
    }
//首页展示
    private void doShow(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        List<Goods> goodsList = shopMallService.selectAll();
        request.setAttribute("goodsList",goodsList);
        request.getRequestDispatcher("/page1.jsp").forward(request,response);
    }
//搜索
    private void doSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        List<Goods> list = shopMallService.searchGoods(search);
        request.setAttribute("list",list);
        request.getRequestDispatcher("/search.jsp").forward(request,response);
    }
    private void doWelcome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取cookie
        //实现十天免登录
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("username".equals(name)) {
                    username = cookie.getValue();
                } else if ("password".equals(name)) {
                    password = cookie.getValue();
                }
            }
        }
        if (username != null && password != null) {//判断用户名和密码是否为空
            //判断用户名和密码是否正确
            boolean success = shopMallService.selectUser(username, password);
            if (success) {
                //获取session
                request.getRequestDispatcher("/page2").forward(request,response);
            } else {
                request.getRequestDispatcher("/page2").forward(request,response);
            }
        }
        request.getRequestDispatcher("/page2").forward(request,response);
    }
}




