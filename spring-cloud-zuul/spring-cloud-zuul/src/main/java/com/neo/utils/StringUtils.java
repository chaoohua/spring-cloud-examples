package com.neo.utils;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

public class StringUtils {
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }
    public static boolean validationSignature(String nonce, String timestamp,
                                                  String signature, long appId) throws Exception {

        String secret = "STCevzDS6Xy18n";
        StringBuilder toSign = new StringBuilder(secret).append(nonce).append(timestamp);
        String sign = hexSHA1(toSign.toString());
        if (!org.apache.commons.lang.StringUtils.equalsIgnoreCase(sign, signature)) {
            return false;
        }

        return true;
    }
    public static String hexSHA1(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(value.getBytes("utf-8"));
            byte[] digest = md.digest();
            return byteToHexString(digest);
        } catch (Exception var3) {
            throw new RuntimeException(var3);
        }
    }
    public static String byteToHexString(byte[] bytes) {
        return Hex.encodeHexString(bytes);
    }

}
