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

    //JWT: 生成并发给客户端之后，后台是不用存储，客户端访问时会验证其签名、过期时间等再取出里面的信息（如username），再使用该信息直接查询用户信息完成登录验证。jwt自带签名、过期等校验，后台不用存储，缺陷是一旦下发，服务后台无法拒绝携带该jwt的请求（如踢除用户）；
    //
    //token+redis： 是自己生成个32位的key，value为用户信息，访问时判断redis里是否有该token，如果有，则加载该用户信息完成登录。服务需要存储下发的每个token及对应的value，维持其过期时间，好处是随时可以删除某个token，阻断该token继续使用

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
