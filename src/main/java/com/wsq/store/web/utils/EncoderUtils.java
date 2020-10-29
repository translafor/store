package com.wsq.store.web.utils;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description:提供各种加密工具方法
 * @projectName:store
 * @see:com.wsq.store.web.utils
 * @author:吴仕权
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
     * author 吴仕权
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
}
