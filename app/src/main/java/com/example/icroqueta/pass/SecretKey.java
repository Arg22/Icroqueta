package com.example.icroqueta.pass;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class SecretKey {
    private static SecretKeySpec secret;


    public static SecretKeySpec generateKey(String password){

        return secret = new SecretKeySpec(password.getBytes(), "AES");
    }

    public static byte[] encryptMsg(String message){
        /* Encrypt the message. */
        Cipher cipher = null;
        byte[] cipherText=null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, (Key) secret);
            cipherText = cipher.doFinal(message.getBytes("UTF-8"));
            return cipherText;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    public static String decryptMsg(byte[] cipherText){
      /* Decrypt the message, given derived encContentValues and initialization vector. */
        Cipher cipher = null;
        String decryptString="";
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, (Key) secret);
            decryptString = new String(cipher.doFinal(cipherText), "UTF-8");
            return decryptString;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decryptString;
    }
}