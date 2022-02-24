package com.yuheng.pangolin.encryption;

import org.junit.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class Encryptor {

    @Test
    public void test() throws GeneralSecurityException {
        String salt = Encryptor.generateHmacMD5Salt();
        String pass = "fyh666fyh";
        System.out.println(salt);
        System.out.println(Encryptor.hmacMD5(pass, salt));
    }

    // AES
    public static String encrypt(String key, String plainText) throws GeneralSecurityException {
        return "";
    }

    public static String decrypt(String key, String encrypted) throws GeneralSecurityException {
        return "";
    }

    // RSA
    public static String rsaEncrypt(String key, String plainText) {
        return "";
    }

    public static String rsaDecrypt(String key, String encrypted) {
        return "";
    }

    // Base64
    public static String base64Encode(String plainText) {
        return Base64.getEncoder().encodeToString(plainText.getBytes());
    }

    public static String base64Decode(String encoded) {
        return new String(Base64.getDecoder().decode(encoded));
    }

    // HmacMD5
    public static String generateHmacMD5Salt() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
            SecretKey key = keyGen.generateKey();
            byte[] skey = key.getEncoded();
            return new BigInteger(1, skey).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String hmacMD5(String message, String salt) {
        try {
            SecretKeySpec key = new SecretKeySpec(salt.getBytes(), "HmacMD5");
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(key);
            mac.update(message.getBytes(StandardCharsets.UTF_8));
            byte[] result = mac.doFinal();
            return new BigInteger(1, result).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // UUID
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
