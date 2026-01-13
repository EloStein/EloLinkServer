package de.spring.elolink_spring.encryption;

import java.security.Key;
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



}
