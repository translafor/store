package com.wsq.store.common.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @description:
 * @projectName:store
 * @see:com.wsq.store.common.config
 * @author:translafor
 * @createTime:2020/11/2 19:41
 * @version:1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserNotifyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code = -1;
    public UserNotifyException(int code,String message){
        //super关键字可以在子类的构造方法中显示地调用父类的构造方法   https://www.cnblogs.com/bluetree2/p/10784962.html
        super(message);
        code = code;
    }

    public UserNotifyException(String message) {
        super(message);
    }
}
