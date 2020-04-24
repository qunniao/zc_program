package com.zhancheng.core.commom;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * @author tangchao
 */
public class SignUtil {

    /**
     * 生成UUID
     *
     * @return 去除-的UUID
     */
    public static String generateNonceStr() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    /**
     * 生成 MD5
     *
     * @param data 需要加密的字符串
     * @return MD5结果
     */
    public static String MD5(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 生成MD5加盐
     *
     * @param data 需要加密的字符串
     * @param salt 盐
     * @return MD5结果
     */
    public static String MD5(String data, String salt) {
        return MD5(data + salt);
    }


}
