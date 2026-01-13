package de.spring.elolink_spring.encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.PrivateKey;

public class Decrypter {

    public static SecretKey decryptAesKey(PrivateKey privateKey, byte[] encryptedAesKey) throws Exception {
        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        rsaCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedKeyBytes = rsaCipher.doFinal(encryptedAesKey);
        return new SecretKeySpec(decryptedKeyBytes, "AES");
    }

    public static byte[] decryptText(byte[] encryptedText, SecretKey aesKey, GCMParameterSpec spec) throws Exception {
        Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");
        System.out.println(aesKey);
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey, spec);
        return aesCipher.doFinal(encryptedText);
    }
}
