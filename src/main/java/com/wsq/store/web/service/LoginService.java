package com.wsq.store.web.service;

import com.wsq.store.common.config.CommonConfig;
import com.wsq.store.common.config.UserNotifyException;
import com.wsq.store.common.domain.user.User;
import com.wsq.store.common.mapper.UserMapper;
import com.wsq.store.web.enums.ExceptionEnums;
import com.wsq.store.web.utils.StringHandleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

/**
 * @description:
 * @projectName:store
 * @see:com.wsq.store.web.service
 * @author:translafor
 * @createTime:2020/11/6 18:03
 * @version:1.0
 */
@Service
public class LoginService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    CommonConfig commonConfig;


    private static final String PHONE = "phone";
    private static final String USER_PASSWORD = "password";

    public Object login(HttpServletRequest rps, HttpServletResponse rpo) {
        //手机号需要用正则做格式判断
        String phone = rps.getParameter(PHONE);
        boolean isPhone = StringHandleUtils.checkIsMobilePhone(phone);
        if(!isPhone){
            throw new UserNotifyException(ExceptionEnums.NOT_PHONE.getCode(),ExceptionEnums.NOT_PHONE.getMsg());
        }
        String password = rps.getParameter(USER_PASSWORD);

        //获取用户
        User userSelect = new User();
        userSelect.setFPhone(phone);
        List<User> userList = userMapper.selectBySelective(userSelect);
        if(CollectionUtils.isEmpty(userList)){
            throw new UserNotifyException(ExceptionEnums.NOT_EXIST_PHONE.getCode(),ExceptionEnums.NOT_EXIST_PHONE.getMsg());
        }
        if(userList.size()>1){
//            LoggerH
        }
        return null;
    }
}
