package com.wsq.store.common.cacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

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


    //再加一层本地缓存：https://blog.csdn.net/qb170217/article/details/81484139
    @Autowired
    private RedisTemplate<String,T> redisTemplate;

    public void addStringValue(String key, T value, long timeout, TimeUnit unit){
        redisTemplate.opsForValue().set(key,value,timeout,unit);
    }

    public T getStringValue(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
