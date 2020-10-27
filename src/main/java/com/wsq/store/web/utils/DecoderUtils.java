package com.wsq.store.web.utils;

import sun.misc.BASE64Decoder;

import java.io.IOException;

public class DecoderUtils {
    // 使用JDK提供的api实现Base64
    private static final String BASE64_WAY_JDK = "1";
    // 使用三方-Commons Codes实现Base64
    private static final String BASE64_WAY_COMMONCODES = "2";
    // 使用三方-Bouncy Castle实现Base64
    private static final String BASE64_WAY_BOUNCYCASTLE = "3";

    /**
     * description base64解密
     * param [encode, base64Way]
     * return java.lang.String
     * author 吴仕权
     * createTime 2020/10/27 20:34
     **/
    public static String baseDecoder(String encode,String base64Way){
        String decode = "";
        switch (base64Way){
            case BASE64_WAY_JDK:
                decode = getBase64ByJDKDecode(encode);
                break;
            case BASE64_WAY_COMMONCODES:
                decode = getBase64ByCommoncodes(encode);
                break;
            case BASE64_WAY_BOUNCYCASTLE:
                decode = getBase64ByBouncyCastle(encode);
                break;
            default:
        }
        return decode;
    }

    private static String getBase64ByJDKDecode(String encode) {
        BASE64Decoder decoder = new BASE64Decoder();
        String decode = "";
        try {
            decode = new String(decoder.decodeBuffer(encode));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return decode;
    }

    private static String getBase64ByBouncyCastle(String src) {
        return "";
    }

    private static String getBase64ByCommoncodes(String src) {
        return "";
    }



}
