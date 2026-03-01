package de.spring.elolink_spring.encryption;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Base64Encoder {

    public static byte[] toBytes(String base64){
        return Base64.getDecoder().decode(base64);
    }

    public static String toBase64(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String toBase64(Key key){
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    public static PrivateKey toPrivateKey(String base64) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(base64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePrivate(keySpec);
    }

    public static PublicKey toPublicKey(String base64) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(base64);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(keySpec);

    }



}
