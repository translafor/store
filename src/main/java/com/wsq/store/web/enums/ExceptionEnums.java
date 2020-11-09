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

    NOT_PHONE(-100,"手机号格式不正确"),
    NOT_EXIST_PHONE(-101,"手机号未注册");
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
