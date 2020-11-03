package com.wsq.store.web.enums;

/**
 * @description:访问路径与权限名称的对应(需要考虑到，一个接口路径可能对应几个前端页面？如何解决？）
 * @projectName:store
 * @see:com.wsq.store.web.enums
 * @author:translafor
 * @createTime:2020/11/3 21:11
 * @version:1.0
 */
public enum PermissionUrlEnum {

    ALL("/","所有功能"),

    LOGIN_ALL("/login/**","登录所有功能"),

    BUYER_ORDERLIST("buyer/order/orderList","买家订单列表")
    ;

    private String code;
    private String name;

    PermissionUrlEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
