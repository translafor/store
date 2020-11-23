package com.wsq.store.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description:
 * @projectName:store
 * @see:com.wsq.store.web.controller
 * @author:translafor
 * @createTime:2020/11/23 21:01
 * @version:1.0
 */
@RestController
public class ModelAndViewController {
    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }


}
