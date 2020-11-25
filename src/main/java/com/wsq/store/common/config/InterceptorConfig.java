package com.wsq.store.common.config;

import com.wsq.store.web.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @projectName:store
 * @see:com.wsq.store.common.config
 * @author:translafor
 * @createTime:2020/11/24 21:11
 * @version:1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //需要拦截的路径，/**表示需要拦截所有请求
        String[]addPathPatterns={"/**"};
        //不需要拦截的路径
        String [] excludePathPaterns={
                "/login",
                "/logins"
        };

        //注册一个登录拦截器
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(addPathPatterns)
                .excludePathPatterns(excludePathPaterns);
    }

//https://www.cnblogs.com/shamo89/p/8534580.html
//    public ApiEncyptInterceptor
}
