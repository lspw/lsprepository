package com.servlet.lsp.backstage.ht.web;

import com.servlet.lsp.backstage.ht.pojo.UserInfro;
import com.servlet.lsp.backstage.ht.service.BackStageService;
import com.servlet.lsp.backstage.ht.pojo.Goods;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet({"/stage/list","/stage/delete","/stage/save","/stage/modify","/stage/infor","/showorder"})
public class BackStageServlet extends HttpServlet {
    BackStageService backStageService=new BackStageService();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String servletPath = request.getServletPath();
        if ("/stage/list".equals(servletPath)) {
            doList(request, response);
        } else if ("/stage/delete".equals(servletPath)) {
            doDel(request, response);//删除
        } else if ("/stage/save".equals(servletPath)) {
            doSave(request, response);//添加
        }else if ("/stage/modify".equals(servletPath)) {
            doModify(request, response);
        }else  if ("/stage/infor".equals(servletPath)){
            doInfor(request,response);
        }else  if ("/showorder".equals(servletPath)){
            doShowOrder(request,response);
        }
    }

    private void doShowOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<UserInfro> goods = backStageService.selectOrder();
        request.setAttribute("goodsList",goods);
        request.getRequestDispatcher("/order.jsp").forward(request,response);
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goodsname=request.getParameter("goodsname");
        String goodsprice=request.getParameter("goodsprice");
        String goodscategory=request.getParameter("goodscategory");
        String goodshome=request.getParameter("goodshome");
        String goodsnum=request.getParameter("goodsnum");
        String goodscount=request.getParameter("goodscount");
        String goodsimg=request.getParameter("goodsimg");
        backStageService.modifyGoodsInfro(goodsname, goodsprice, goodscategory, goodshome,
                goodsnum, goodscount, goodsimg);
        response.sendRedirect(request.getContextPath()+"/stage/list");//重定向
    }

    private void doInfor(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String  goodsname = request.getParameter("goodsname");
        String  goodshome = request.getParameter("goodshome");
        String  goodsnum = request.getParameter("goodsnum");
        List<Goods> goods = backStageService.aboriginalInfro(goodsname, goodsnum, goodshome);
        request.setAttribute("goods",goods);
        request.getRequestDispatcher("/modify.jsp").forward(request,response);
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String  goodsname = request.getParameter("goodsname");
        String  goodshome = request.getParameter("goodshome");
        String  goodsnum = request.getParameter("goodsnum");
        backStageService.deleteGoods(goodsname,goodsnum,goodshome);
        response.sendRedirect(request.getContextPath()+"/stage/list");
    }

    private void doSave(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String goodsname=request.getParameter("goodsname");
        String goodsprice=request.getParameter("goodsprice");
        String goodscategory=request.getParameter("goodscategory");
        String goodshome=request.getParameter("goodshome");
        String goodsnum=request.getParameter("goodsnum");
        String goodscount=request.getParameter("goodscount");
        String goodsimg=request.getParameter("goodsimg");
        backStageService.addGoodsByAll(goodsname,goodsprice,goodscategory,goodshome,goodsnum,goodscount,goodsimg);
        response.sendRedirect(request.getContextPath()+"/stage/list");
    }

    private void doList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        List<Goods> goods = backStageService.selectAllGoods();
        request.setAttribute("goodsList",goods);
        request.getRequestDispatcher("/List.jsp").forward(request,response);
    }

}
