package com.wsq.store.web.utils;

import sun.misc.BASE64Decoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

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
     * author translafor
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


    /**
     * description AES解密
     * 解密
     *      * 解密过程：
     *      * 1.同加密1-4步
     *      * 2.将加密后的字符串反纺成byte[]数组
     *      * 3.将加密内容解密
     * param [encodeRules, content]
     * return java.lang.String
     * author translafor
     * createTime 2020/10/30 10:20
     **/
    public static String aesDncode(String encodeRules,String content){
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            //3.产生原始对称密钥
            SecretKey original_key=keygen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte [] raw=original_key.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKey key=new SecretKeySpec(raw, "AES");


            //6.根据指定算法AES自成密码器
            Cipher cipher=Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
            /*
             * 解密
             */
            byte [] byte_decode=cipher.doFinal(byte_content);


            String AES_decode=new String(byte_decode,"utf-8");
            return AES_decode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        //如果有错就返加nulll
        return null;
    }


}
