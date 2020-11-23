package com.wsq.store.web.controller;

import com.wsq.store.common.config.UserNotifyException;
import com.wsq.store.common.domain.base.ResponseResult;
import com.wsq.store.web.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
//@RequestMapping("/user")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * 通过手机号+密码登录
     * @param rps
     * @param rpo
     */
    @GetMapping("/logins")
    public ResponseResult<?> userLogin(HttpServletRequest rps, HttpServletResponse rpo){
        try {
            return ResponseResult.success(loginService.login(rps,rpo));
        }catch (UserNotifyException e){
            logger.error(e.getMessage(),e);
            return ResponseResult.fail(e.getCode(),e.getMessage());
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return ResponseResult.fail(e.getMessage());
        }


    }
}
