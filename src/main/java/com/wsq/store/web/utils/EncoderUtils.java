package com.wsq.store.web.utils;

import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @description:提供各种加密工具方法
 * @projectName:store
 * @see:com.wsq.store.web.utils
 * @author:translafor
 * @createTime:2020/10/26 20:34
 * @version:1.0
 */
public class EncoderUtils {

    // 使用JDK提供的api实现Base64
    private static final String BASE64_WAY_JDK = "1";
    // 使用三方-Commons Codes实现Base64
    private static final String BASE64_WAY_COMMONCODES = "2";
    // 使用三方-Bouncy Castle实现Base64
    private static final String BASE64_WAY_BOUNCYCASTLE = "3";
    /**
     * description base64加密方法
     * param [src]
     * return java.lang.String
     * author translafor
     * createTime 2020/10/26 20:37
     **/
    public static String base64Emcode(String src,String base64Way){
        String encode = "";
        switch (base64Way){
            case BASE64_WAY_JDK:
                encode = getBase64ByJDK(src);
                break;
            case BASE64_WAY_COMMONCODES:
                encode = getBase64ByCommoncodes(src);
                break;
            case BASE64_WAY_BOUNCYCASTLE:
                encode = getBase64ByBouncyCastle(src);
                break;
                default:
        }
        return encode;
    }

    private static String getBase64ByJDK(String src) {
        BASE64Encoder encoder = new BASE64Encoder();
        return  encoder.encode(src.getBytes());
    }

    private static String getBase64ByCommoncodes(String src) {
        return "";
    }

    private static String getBase64ByBouncyCastle(String src) {
        return "";
    }

    private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    private static String bytesToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            int n = b[i];
            if (n < 0) {
                n += 256;
            }
            int d1 = n / 16;
            int d2 = n % 16;
            resultSb.append(hexDigits[d1] + hexDigits[d2]);
        }
        return resultSb.toString();
    }


    /**    https://juejin.im/post/6844903850160160781
     * description
     * param [origin, charsetname]
     * return java.lang.String
     * author translafor
     * createTime 2020/10/29 21:47
     **/
    public static String jdkMD5(String origin,String charsetname){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            if ((charsetname == null) || ("".equals(charsetname))) {
                return bytesToHexString(md.digest(origin.getBytes()));
            } else {
                return bytesToHexString(md.digest(origin.getBytes(charsetname)));
            }
        } catch (Exception localException) {
        }
        return "";
    }



    /**
     * description AES加密 --* 加密
     *    * 1.构造密钥生成器
     *    * 2.根据ecnodeRules规则初始化密钥生成器
     *    * 3.产生密钥
     *    * 4.创建和初始化密码器
     *    * 5.内容加密
     *    * 6.返回字符串
     * param [encodeRules, content]
     * return java.lang.String
     * author translafor
     * createTime 2020/10/30 9:40
     **/
    public static String AESEncode(String encodeRules,String content){
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            //3.产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
            System.out.println(original_key);
            //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(raw, "AES");


            //6.根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte [] byte_encode=content.getBytes("utf-8");
            //9.根据密码器的初始化方式--加密：将数据加密
            byte [] byte_AES=cipher.doFinal(byte_encode);


            //10.将加密后的数据转换为字符串
            //这里用Base64Encoder中会找不到包
            //解决办法：
            //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
            String aes_encode=new String(new BASE64Encoder().encode(byte_AES));
            //11.将字符串返回
            return aes_encode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //如果有错就返加nulll
        return null;
    }
}
