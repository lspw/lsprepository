package com.lsp.crm.settings.interceptor;

import com.lsp.crm.commons.contants.Contants;
import com.lsp.crm.settings.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //跳转到页面之前，验证是否登录
        HttpSession session = request.getSession();
        User user= (User)session.getAttribute(Contants.SESSION_USER);
        if (user==null) {
            //user等于null说明没有登录跳转到登录页面
            response.sendRedirect(request.getContextPath());
            return false;
        }
        //否则跳转到响应页面
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
