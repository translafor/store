package com.wsq.store.web.utils;

/**
 * @description:
 * @projectName:store
 * @see:com.wsq.store.web.utils
 * @author:吴仕权
 * @createTime:2020/10/27 20:03
 * @version:1.0
 */
public class EncoderUtilsTest {

    static  void testBase64(){
        String encodeString = EncoderUtils.base64Emcode("wsqddd","1");
        String decoderString = DecoderUtils.baseDecoder(encodeString,"1");
        System.out.println(encodeString);
        System.out.println(decoderString);
    }

    public static void main(String[] args) {
        testBase64();
    }

}
