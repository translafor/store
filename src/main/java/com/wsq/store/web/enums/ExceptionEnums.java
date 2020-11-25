package com.wsq.store.web.enums;

/**
 * @description:
 * @projectName:store
 * @see:com.wsq.store.web.enums
 * @author:translafor
 * @createTime:2020/11/4 20:35
 * @version:1.0
 */
public enum ExceptionEnums {
    USER_NOT_LOGIN(-10,"用户未登录"),
    NOT_PHONE(-100,"手机号格式不正确"),
    NOT_EXIST_PHONE(-101,"手机号未注册,请前往注册"),
    USERINFO_ERROR(-102,"用户信息异常"),
    PASSWROD_ERROR(-103,"密码错误"),
    ENMPTY_PHONE(-104,"手机号为空"),
    ENMPT_PASSWORD(-105,"密码为空");
    private int code;
    private String msg;

    private ExceptionEnums (int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
