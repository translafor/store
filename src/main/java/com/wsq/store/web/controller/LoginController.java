package com.wsq.store.web.controller;

import com.wsq.store.common.config.CommonConfig;
import com.wsq.store.common.config.UserNotifyException;
import com.wsq.store.common.domain.base.ResponseResult;
import com.wsq.store.common.domain.user.User;
import com.wsq.store.common.mapper.UserMapper;
import com.wsq.store.web.enums.ExceptionEnums;
import com.wsq.store.web.service.LoginService;
import com.wsq.store.web.utils.StringHandleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/user")
public class LoginController {
    @Autowired
    LoginService loginService;

    /**
     * 通过手机号+密码登录
     * @param rps
     * @param rpo
     */
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ResponseResult<?> userLogin(HttpServletRequest rps, HttpServletResponse rpo){
        try {
            return ResponseResult.success(loginService.login(rps,rpo));
        }catch (UserNotifyException e){
            return ResponseResult.fail(e.getCode(),e.getMessage());
        }catch (Exception e){
            return ResponseResult.fail(e.getMessage());
        }


    }
}
