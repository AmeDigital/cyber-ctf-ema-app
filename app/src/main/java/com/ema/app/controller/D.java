package com.ema.app.controller;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class D {
//    private static String secret = "e8d95a51f3af4a3b134bf6bb680a213a";
//    private static byte[] secure_flag =
//                      { -22, -93, 70, 1, 36, -25, 50, -71, -104, 81, -62, 117, -72, -42, 75, 36, -113, -105, -81, -23, -28, -32, 22, -77, 21,
//                              -25, 13, 15, 65, -3, 92, -114, -83, -4, 108, 97, -128, -128, 121, 119, -45, -45, 94, -73, 49, -128, 127, 8 };

    //TODO change the hash and secure_flag
    private static byte[] secure_flag =
                        { 103, 125, -20, -127, 56, -13, 13, 72, -13, 18, 16, 97, 69, 126, -42, 125, -18, 16, 82, -60, -66, 71, -74, 69, -78,
                                -80, -98, 126, -105, -96, -41, 123, 95, -2, -68, -94, 124, 30, 94, 59, -98, 39, 43, 68, -13, 82, 31, 124 };
    private static String secret = "882c386b7095e25c3801b6e4bbfc21b6";

//TODO REMOVE THIS METHOD
//    public static byte[] encrypt(byte[] strClearText,String strKey) throws Exception {
//        byte[] encrypted;
//        try {
//            SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(), "Blowfish");
//            Cipher cipher = Cipher.getInstance("Blowfish");
//            cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
//            encrypted = cipher.doFinal(strClearText);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new Exception(e);
//        }
//        return encrypted;
//    }

    public static byte[] decrypt(byte[] strEncrypted, String strKey) throws Exception {
        byte[] decrypted;

        try {
            SecretKeySpec skeyspec = new SecretKeySpec(strKey.getBytes(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, skeyspec);

            decrypted = cipher.doFinal(strEncrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return decrypted;
    }

    public static String getHash() { return secret; }

    //FLAG1
    public String getFlag() throws Exception {
        byte[] dec = decrypt(this.secure_flag, this.secret);
        String flag = new String(dec, StandardCharsets.UTF_8);

        return flag;
    }

    //FLAG2
    public String getFlag(String secret, byte[] secure_flag) throws Exception {
        byte[] secure = decrypt(secure_flag, secret);
        byte[] dec = decrypt(secure, this.secret);
        String flag = new String(dec, StandardCharsets.UTF_8);

        return flag;
    }

    //TODO REMOVE THIS
//    private void setFlag() throws Exception {
//      String flag = "FLAG1";
//      byte[] enc = encrypt(flag.getBytes(StandardCharsets.UTF_8),this.secret);
//
//        String flag = "FLAG#2_{N-T_Ill_PuT_Secrets_Open_on_R4m}";
//        byte[] enc = encrypt(flag.getBytes(StandardCharsets.UTF_8),this.secret);
//
//        //System.out.println(Arrays.toString(enc));
//
//        //String secret = "<EMPTY>";
//        String secret = "1HmgYQ7g^C%y7HzX9$RiC5ArGryGjVjzNb06gyp$l8DN!hfrHeDt28Yze2vU6DE8XLVJws";
//        byte[] round2 = encrypt(enc,secret);
//
//        System.out.println(Arrays.toString(round2));
//    }

}