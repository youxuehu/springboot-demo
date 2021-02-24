package com.example.springbootdemo.utils.guava.集合;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 加密解密
 *
 */
public class Base64Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(Base64Test.class);

    public static void main(String[] args) {
        String str = "程序员";
        Base64 base64 = new Base64();
        String base64Str ;
        try {
            //Base64编码
            base64Str = base64.encodeToString(str.getBytes("UTF-8"));
            System.out.println(base64Str);
        }catch (Exception e){
            LOGGER.error("文本Base64编码异常",e);
            return;
        }
        //Base64解码
        byte[] decode = base64.decode(base64Str);
        try {

            String rs = new String(decode,"utf-8");
            System.out.println(rs);
        }catch (Exception e){
            LOGGER.error("文本解码异常",e);
        }
    }

}
