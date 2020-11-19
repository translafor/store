package com.wsq.store.web.service;

import com.wsq.store.common.cacher.RedisService;
import com.wsq.store.common.config.CommonConfig;
import com.wsq.store.common.config.UserNotifyException;
import com.wsq.store.common.constant.LoginConstant;
import com.wsq.store.common.domain.user.User;
import com.wsq.store.common.mapper.UserMapper;
import com.wsq.store.web.enums.ExceptionEnums;
import com.wsq.store.web.utils.EncoderUtils;
import com.wsq.store.web.utils.StringHandleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


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
    @Autowired
    RedisService redisService;
    Logger logger = LoggerFactory.getLogger(LoginService.class);

    private static final String PHONE = "phone";
    private static final String USER_PASSWORD = "password";

    public void login(HttpServletRequest rps, HttpServletResponse rpo) {
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
            logger.info(String.format("手机号%s对应多个用户",userSelect.getFPhone()));
            throw new UserNotifyException(ExceptionEnums.USERINFO_ERROR.getCode(),ExceptionEnums.USERINFO_ERROR.getMsg());
        }
        User user = userList.get(0);
        if(!password.equals(EncoderUtils.AESEncode(commonConfig.getAesKey(),user.getFPassword()))){
            throw new UserNotifyException(ExceptionEnums.PASSWROD_ERROR.getCode(),ExceptionEnums.PASSWROD_ERROR.getMsg());
        }

        //采用token+redis 不用jwt的原因在于jwt无法主动踢出用户
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        redisService.addStringValue(uuid,user,2, TimeUnit.MINUTES);
        rpo.addHeader(LoginConstant.AUTH_TOKEN,uuid);
    }
}
