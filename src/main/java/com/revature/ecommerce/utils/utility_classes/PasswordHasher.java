package com.revature.ecommerce.utils.utility_classes;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

public class PasswordHasher {
    public static String hash(String password) {

        try {
            //SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            //random.nextBytes(salt);
            for (int i = 0; i < 16; i++)
                salt[i] = (byte) i;
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return new String(hash);
        } catch (GeneralSecurityException e) {
            System.out.println(e);
        }
        return null;
    }
}
