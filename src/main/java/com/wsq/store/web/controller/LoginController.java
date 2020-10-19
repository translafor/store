package com.wsq.store.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class LoginController {

    private static final String USER_NAME = "userName";
    private static final String USER_PASSWORD = "password";

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public void userLogin(HttpServletRequest rps, HttpServletResponse rpo){
        String userName = rps.getParameter(USER_NAME);
        String password = rps.getParameter(USER_PASSWORD);

    }
}
