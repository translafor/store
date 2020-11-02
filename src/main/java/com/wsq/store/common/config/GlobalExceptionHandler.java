package com.wsq.store.common.config;

import com.wsq.store.common.domain.base.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:全局异常处理
 * @projectName:store
 * @see:com.wsq.store.common.config
 * @author:translafor
 * @createTime:2020/11/2 19:33
 * @version:1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * description 用来处理UserNotifyException，主要是对用户的提示性异常
     * param 
     * return 
     * author translafor
     * createTime 2020/11/2 20:01
     **/
    @ResponseBody//ResponseBody作用和原理：https://blog.csdn.net/jiahao1186/article/details/91980316   https://www.cnblogs.com/myjjy/p/8717435.html
    @ExceptionHandler(value = UserNotifyException.class)
    public ResponseResult<?> userNotifyExceptionHandler(HttpServletRequest httpServletRequest,UserNotifyException exception){
        return ResponseResult.fail(exception.getCode(),exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseResult<?> exceptionHandler(HttpServletRequest httpServletRequest,Exception exception){
        //instanceof:https://blog.csdn.net/kuangay/article/details/81563992
        if(exception instanceof UserNotifyException){
//            UserNotifyException userNotifyException
//            return ResponseResult.fail(exception.getCode(),exception.getMessage());
        }
        return null;
    }
}
