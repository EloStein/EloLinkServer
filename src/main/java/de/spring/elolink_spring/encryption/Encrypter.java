package de.spring.elolink_spring.encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.PublicKey;

public class Encrypter {

    public static byte[] encryptAesKey(PublicKey publicKey, SecretKey aesKey) throws Exception{
        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return rsaCipher.doFinal(aesKey.getEncoded());
    }

    public static byte[] encryptText(String text, SecretKey aesKey, GCMParameterSpec spec) throws Exception {
        Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");
        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey, spec);
        return aesCipher.doFinal(text.getBytes());
    }

}


