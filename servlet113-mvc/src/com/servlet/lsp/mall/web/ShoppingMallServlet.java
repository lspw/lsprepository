package com.servlet.lsp.mall.web;

import com.servlet.lsp.mall.pojo.User;
import com.servlet.lsp.mall.service.ShopMallService;
import com.servlet.lsp.mall.service.ShopUserMallService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet({"/login","/logon"})
public class ShoppingMallServlet extends HttpServlet {
    ShopUserMallService shopUserMallService=new ShopUserMallService();
    ShopMallService shopMallService=new ShopMallService();
    @Override
    //登录和注册
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/login".equals(servletPath)) {
            doLogin(request, response);
        } else if ("/logon".equals(servletPath)) {
            doLogon(request, response);
        }
    }


    //登录
    private void doLogon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean success = shopUserMallService.Logon(username, password);
        if(success){
            //获取session对象
            HttpSession session = request.getSession();
            /*  session.setAttribute("username",username);*/
            User user=new User(username,password);
            session.setAttribute("user",user);

            //实现10天免登录
            String f = request.getParameter("f");
            if("1".equals(f)) {
                //创建cookie
                Cookie cookie1=new Cookie("username",username);
                Cookie cookie2=new Cookie("password",password);
                cookie1.setMaxAge(60*60*24*10);
                cookie2.setMaxAge(60*60*24*10);
                cookie1.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());
                response.addCookie(cookie1);
                response.addCookie(cookie2);
                response.sendRedirect(request.getContextPath()+"/page2");
            }else {
                response.sendRedirect(request.getContextPath()+"/page2");
            }
        }else {
          response.sendRedirect(request.getContextPath()+"/Logon.jsp");
        }
//注册
    }
    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int count = shopUserMallService.login(username, password);
        if (count == 1) {
            out.print("注册成功");
        } else {
            out.print("注册失败");
        }
    }
}
