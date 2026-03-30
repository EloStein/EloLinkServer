package de.spring.elolink_spring.encryption;

import tools.jackson.core.ObjectReadContext;

import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.util.Arrays;

public class TestEncryption {

    public static void main(String[] args) throws Exception {

        // RSA Generation
        KeyPair receiverRsaKeyPair = KeyGenerator.genRsaKeyPair();
        KeyPair senderRsaKeyPair = KeyGenerator.genRsaKeyPair();

        // Aes Key Generation
        SecretKey aesKey = KeyGenerator.genAesKey();
        System.out.println("Unencrypted Aes Key: " + aesKey);
        byte[] encryptedAesKeyRec = Encrypter.encryptAesKey(receiverRsaKeyPair.getPublic(), aesKey);
        byte[] encryptedAesKeyOwn = Encrypter.encryptAesKey(senderRsaKeyPair.getPublic(), aesKey);

        // Private and Public Key to Base64
        String publicKeyBase64Rec = Base64Encoder.toBase64(receiverRsaKeyPair.getPublic());
        String privateKeyBase64Rec = Base64Encoder.toBase64(receiverRsaKeyPair.getPrivate());
        String publicKeyBase64Own = Base64Encoder.toBase64(senderRsaKeyPair.getPublic());
        String privateKeyBase64Own = Base64Encoder.toBase64(senderRsaKeyPair.getPrivate());

        System.out.println("PUBLIC KEY BASE64 REC: " + publicKeyBase64Rec);
        System.out.println("PRIVATE KEY BASE64 REC: " + privateKeyBase64Rec);
        System.out.println("PUBLIC KEY BASE64 OWN: " + publicKeyBase64Own);
        System.out.println("PRIVATE KEY BASE64 OWN: " + privateKeyBase64Own);

        // Encrypted AesKey to AesKeyBase64
        String AesKeyBase64Rec = Base64Encoder.toBase64(encryptedAesKeyRec);
        String AesKeyBase64Own = Base64Encoder.toBase64(encryptedAesKeyOwn);

        System.out.println("AesKey Rec Base64: " + AesKeyBase64Rec);
        System.out.println("AesKey Own Base64: " + AesKeyBase64Own);

        // Test: Decrypted AesKey
        //System.out.println("Rec decrypted Aes Key like: " + Decrypter.decryptAesKey(receiverRsaKeyPair.getPrivate(), encryptedAesKeyRec));
        //System.out.println("Own decrypted Aes Key like: " + Decrypter.decryptAesKey(senderRsaKeyPair.getPrivate(), encryptedAesKeyOwn));


        //Encrypting Message
        GCMParameterSpec spec = KeyGenerator.genSpec();
        byte[] encryptedText = Encrypter.encryptText("TestNachricht", aesKey, spec);
        System.out.println("Encrypted Message: " + new String(encryptedText));


        // Decrypting AesKeyBase64 to AesKey
        byte[] reEncryptedAesKeyRec = Base64Encoder.toBytes(AesKeyBase64Rec);
        byte[] reEncryptedAesKeyOwn = Base64Encoder.toBytes(AesKeyBase64Own);

        // Test: Base64 to Private Key; Never publish private Keys!
        PrivateKey reEncryptedPrivateKeyRec = Base64Encoder.toPrivateKey(privateKeyBase64Rec);
        PrivateKey reEncryptedPrivateKeyOwm = Base64Encoder.toPrivateKey(privateKeyBase64Own);

        // Decrypting AesKey
        SecretKey decryptedAesKeyRec = Decrypter.decryptAesKey(reEncryptedPrivateKeyRec, reEncryptedAesKeyRec);
        SecretKey decryptedAesKeyOwn = Decrypter.decryptAesKey(reEncryptedPrivateKeyOwm, reEncryptedAesKeyOwn);

        // Decrypting Message
        byte[] decryptedMessageRec = Decrypter.decryptText(encryptedText, decryptedAesKeyRec, spec);
        byte[] decryptedMessageOwn = Decrypter.decryptText(encryptedText, decryptedAesKeyOwn, spec);
        System.out.println("Decrypted Message by Rec: " + new String(decryptedMessageRec));
        System.out.println("Decrypted Message by Own: " + new String(decryptedMessageOwn));

    }
}
