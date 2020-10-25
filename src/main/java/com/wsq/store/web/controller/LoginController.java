package com.wsq.store.web.controller;

import com.wsq.store.common.domain.user.User;
import com.wsq.store.common.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserMapper userMapper;

    private static final String PHONE = "phone";
    private static final String USER_PASSWORD = "password";

    /**
     * 通过手机号+密码登录
     * @param rps
     * @param rpo
     */
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public void userLogin(HttpServletRequest rps, HttpServletResponse rpo){
        String phone = rps.getParameter(PHONE);
        String password = rps.getParameter(USER_PASSWORD);
        User userSelect = new User();
        userSelect.setFPhone(phone);
//        User user = userMapper.updateByPrimaryKeySelective(userSelect);
    }
}
