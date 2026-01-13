package de.spring.elolink_spring.encryption;

import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

public class KeyGenerator {

    public static KeyPair genRsaKeyPair() throws Exception {
        KeyPairGenerator rsaKeyGen = KeyPairGenerator.getInstance("RSA");
        rsaKeyGen.initialize(2048);
        return rsaKeyGen.generateKeyPair();
    }

    public static SecretKey genAesKey() throws Exception {
        javax.crypto.KeyGenerator keyGen = javax.crypto.KeyGenerator.getInstance("AES");
        keyGen.init(256);
        return keyGen.generateKey();
    }

    public static GCMParameterSpec genSpec() throws Exception {
        byte[] nonce = new byte[12];
        new SecureRandom().nextBytes(nonce);
        return new GCMParameterSpec(128, nonce);
    }

}
