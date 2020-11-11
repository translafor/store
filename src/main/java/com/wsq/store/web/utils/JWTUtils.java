package com.wsq.store.web.utils;

import com.wsq.store.common.config.UserNotifyException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Date;

/**
 * @description:
 * @projectName:store
 * @see:com.wsq.store.web.utils
 * @author:translafor
 * @createTime:2020/11/11 20:14
 * @version:1.0
 */
public class JWTUtils {

    private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);
    /**
     * description 这里只保存userId，用userId去redis取对应的信息
     * param [userId, secreatKey, expire]
     * return java.lang.String
     * author translafor
     * createTime 2020/11/11 20:40
     **/
    public static String generator(String userId, String secretKey, Duration expire){
        Date expireTime = new Date(System.currentTimeMillis()+expire.toMillis());
        return Jwts.builder()
                .setSubject(userId) // 将userName放进JWT
                .setIssuedAt(new Date()) // 设置JWT签发时间
                .setExpiration(expireTime)  // 设置过期时间
                .signWith(SignatureAlgorithm.HS512, secretKey) // 设置加密算法和秘钥
                .compact();
    }

    public static Claims parse(String token,String secretKey){
        if(StringUtils.isEmpty(token)){
            return null;
        }

        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (JwtException e){
            logger.error("JWT解析异常",e);
        }

        return claims;
    }
}
