package com.wsq.store.web.controller;

import com.wsq.store.common.config.UserNotifyException;
import com.wsq.store.common.domain.base.ResponseResult;
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
    @GetMapping("/losssgsin")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView home(ModelAndView modelAndView){
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @GetMapping("/home02")
    public ResponseResult<?> home02(){
        try {
            return ResponseResult.success("success");
        }catch (UserNotifyException e){
            return ResponseResult.fail(e.getCode(),e.getMessage());
        }catch (Exception e){
            return ResponseResult.fail(e.getMessage());
        }
    }
}
