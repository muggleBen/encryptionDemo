package com.popsands.getinfo;

import android.util.Base64;
import android.util.Log;

import com.popsands.mylibrary.AESCryptor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Iterator;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class encryptionUtils {

    private static final String TAG = "EncryptUtils";


    public static byte[] generateKey(String password) throws Exception
    {
        byte[] keyStart = password.getBytes("UTF-8");
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        sr.setSeed(keyStart);
        kgen.init(128, sr);
        SecretKey skey = kgen.generateKey();
        return skey.getEncoded();
//        return InsecureSHA1PRNGKeyDerivator.deriveInsecureKey(password.getBytes(), 32);
    }

    public static byte[] encodeFile(byte[] key, byte[] fileData) throws Exception
    {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(fileData);
        return encrypted;
    }

    public static byte[] decodeFile(byte[] key, byte[] fileData) throws Exception
    {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(fileData);
        return decrypted;
    }

    public static void encrypt(File sourceFile, File destFile, String pwd) {
        int size = (int) sourceFile.length();
        Log.e("msgEenFile", "in："+size);
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(sourceFile));
            buf.read(bytes, 0, bytes.length);
            buf.close();
            byte[] key = generateKey(pwd);
//            byte[] encodedFile = encodeFile(key, bytes);
            byte[] encodedFile = AESCryptor.crypt(bytes, System.currentTimeMillis(), 0);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
            bos.write(encodedFile);
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            Log.e("msgEenFile", e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("msgEenIO", e.toString());
            e.printStackTrace();
        } catch (Exception e) {
            Log.e("msgEen", e.toString());
            e.printStackTrace();
        }
    }

    public static void decrypt(File sourceFile, File destFile, String pwd) {
        int size = (int) sourceFile.length();

        byte[] bytes = new byte[size];
        int length = bytes.length;
        Log.e("msgEdeFile", "in:"+size + "\n" + length);
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(sourceFile));
            buf.read(bytes, 0, length);
            buf.close();
//            byte[] key = generateKey(pwd);
//            byte[] decodedFile = decodeFile(key, bytes);
            byte[] decodedbase64 = Base64.decode(new String(bytes),Base64.DEFAULT);
            byte[] decodedFile = AESCryptor.crypt(decodedbase64, System.currentTimeMillis(), 1);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
            Log.e("msgEdeFileNull", decodedbase64.length + "\n" + decodedFile.length);
            bos.write(decodedFile);
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            Log.e("msgEdeFile", e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("msgEdeIO", e.toString());
            e.printStackTrace();
        } catch (Exception e) {
            Log.e("msgEde", e.toString());
            e.printStackTrace();
        }
    }

//    private static SecretKeySpec deriveKeyInsecurely(String password) {
//        byte[] passwordBytes = password.getBytes(StandardCharsets.US_ASCII);
//        return new SecretKeySpec(InsecureSHA1PRNGKeyDerivator.deriveInsecureKey(passwordBytes, 32), "AES");
//    }

}
