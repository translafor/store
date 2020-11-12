package com.wsq.store.web.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @projectName:store
 * @see:com.wsq.store.web.interceptor
 * @author:translafor
 * @createTime:2020/11/12 20:34
 * @version:1.0
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //需要对登录接口放行

        //获取token
        String token = request.getHeader("authToken");
        if(StringUtils.isEmpty(token)){
//            throw
        }
        return true;
    }
}
