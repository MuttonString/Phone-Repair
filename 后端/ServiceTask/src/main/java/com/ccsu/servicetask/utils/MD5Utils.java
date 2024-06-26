package com.ccsu.servicetask.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/***
 * 对字符串进行MD5加密
 */
public class MD5Utils {
    public static String md5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
