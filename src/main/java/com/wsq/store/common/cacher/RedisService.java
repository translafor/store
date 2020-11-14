package com.wsq.store.common.cacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @projectName:store
 * @see:com.wsq.store.common.cacher
 * @author:translafor
 * @createTime:2020/11/14 18:59
 * @version:1.0
 */
@Component
public class RedisService<T>{
    //https://blog.csdn.net/sinat_22797429/article/details/89196933
    @Autowired
    private RedisTemplate<String,T> redisTemplate;


}
