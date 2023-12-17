package com.servlet.lsp.backstage.ht.web;


import com.servlet.lsp.backstage.ht.pojo.User;
import com.servlet.lsp.backstage.ht.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;


@WebServlet({"/user/login","/user/exit"})
public class UserServlet extends HttpServlet {
    UserService userService=new UserService();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String servletPath = request.getServletPath();
        if("/user/login".equals(servletPath)){
            doLogin(request,response);
        }else if("/user/exit".equals(servletPath)){
            doExit(request,response);
        }
    }
    private void doExit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(false);
        if(session!=null){
            //从session域中删除user对象
            session.removeAttribute("user");
            session.invalidate();
            Cookie[] cookies1 = request.getCookies();
            if (cookies1!=null) {
                for (Cookie cookie : cookies1) {
                    cookie.setMaxAge(0);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
            }
            response.sendRedirect(request.getContextPath());
        }
    }
    protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean success = userService.logon(username, password);
        if(success){
            //获取session对象
            HttpSession session = request.getSession();
              /*session.setAttribute("username",username);*/
            User user=new User(username,password);
            session.setAttribute("user",user);
           /*  response.sendRedirect(request.getContextPath()+"/stage/list");*/
            response.sendRedirect(request.getContextPath()+"/stage/list");
        }else {
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }
    }
}
