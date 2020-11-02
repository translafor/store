package com.wsq.store.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @projectName:store
 * @see:com.wsq.store.common.config
 * @author:translafor
 * @createTime:2020/11/2 18:53
 * @version:1.0
 */
@Component
public class CommonConfig {
    @Value("${store.aes.key}}")
    private String aesKey;

    public String getAesKey() {
        return aesKey;
    }
}
