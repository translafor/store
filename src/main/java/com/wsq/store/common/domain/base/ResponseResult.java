package com.wsq.store.common.domain.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data//get set
@EqualsAndHashCode//equlas hashcode
@NoArgsConstructor//空构造方法
@AllArgsConstructor//全参数构造方法
public class ResponseResult<T> implements Serializable {
    //https://blog.csdn.net/u014750606/article/details/80040130
    private static final  long serialVersionUID = 1L;

    /**
     * 特征码：
     * 0：成功
     * 非0：失败
     */
    private int code;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 消息体
     */
    private T data;

    public ResponseResult(int code,T obj){
        this.code = code;
        if(0!=code){
            msg = (String)obj;
        }
        else{
            data = obj;
        }
    }


    public static ResponseResult success(Object data){
        return new ResponseResult(0,data);
    }

    public static ResponseResult fail(int code,String msg){
        return new ResponseResult(-1,msg);
    }

    public static ResponseResult fail(String msg){
        return new ResponseResult(-1,msg);
    }


}
