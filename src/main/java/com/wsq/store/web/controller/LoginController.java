package com.wsq.store.web.controller;

import com.wsq.store.common.config.CommonConfig;
import com.wsq.store.common.config.UserNotifyException;
import com.wsq.store.common.domain.user.User;
import com.wsq.store.common.mapper.UserMapper;
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
    UserMapper userMapper;
    @Autowired
    CommonConfig commonConfig;

    private static final String PHONE = "phone";
    private static final String USER_PASSWORD = "password";

    /**
     * 通过手机号+密码登录
     * @param rps
     * @param rpo
     */
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public void userLogin(HttpServletRequest rps, HttpServletResponse rpo){
        //手机号需要用正则做格式判断
        String phone = rps.getParameter(PHONE);
        boolean isPhone = StringHandleUtils.checkIsMobilePhone(phone);
        if(!isPhone){
//            throw new UserNotifyException("qing")
        }
        String password = rps.getParameter(USER_PASSWORD);

        //获取用户
        User userSelect = new User();
        userSelect.setFPhone(phone);
        List<User> userList = userMapper.selectBySelective(userSelect);

    }
}
