package com.wsq.store.web.interceptor;

import com.wsq.store.common.cacher.RedisService;
import com.wsq.store.common.config.UserNotifyException;
import com.wsq.store.common.constant.LoginConstant;
import com.wsq.store.web.enums.ExceptionEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //需要对登录接口放行

        //获取token
        String token = request.getHeader(LoginConstant.AUTH_TOKEN);
        if(StringUtils.isEmpty(token)){
            throw UserNotifyException.buildUserNotifyException(ExceptionEnums.USER_NOT_LOGIN);
        }
        Object userInfo = redisService.getStringValue(token);
        if(null==userInfo){
            //登录失败，跳转到登录页
            response.sendRedirect(request.getContextPath());
            return false;
        }
        return true;
    }
}
