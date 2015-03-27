package com.rubblesnask.core;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Johska on 27.03.2015.
 */
public class HashingDemo {

    public static String generateHash(String password){
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = createSalt().getBytes();
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64*8);
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = secretKeyFactory.generateSecret(spec).getEncoded();
            return toHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

    }

    public static String createSalt()  {
        try {
            SecureRandom rand = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[16];
            rand.nextBytes(salt);
            return salt.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
}
