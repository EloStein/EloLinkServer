package de.spring.elolink_spring.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.*;
import java.util.Base64;

public class TestEncryption {

    // AES-GCM Parameter
    private static final int AES_KEY_SIZE = 256; // Bits
    private static final int GCM_NONCE_LENGTH = 12; // Bytes
    private static final int GCM_TAG_LENGTH = 128; // Bits

    public static void main(String[] args) throws Exception {
        // 1️⃣ RSA-Schlüsselpaare generieren (Absender und Empfänger)
        KeyPairGenerator rsaGen = KeyPairGenerator.getInstance("RSA");
        rsaGen.initialize(2048);
        KeyPair senderKeyPair = rsaGen.generateKeyPair();
        KeyPair receiverKeyPair = rsaGen.generateKeyPair();

        // 2️⃣ AES-Session-Key erzeugen
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(AES_KEY_SIZE);
        SecretKey aesKey = keyGen.generateKey();

        // 3️⃣ Session-Key mit RSA des Empfängers verschlüsseln
        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        rsaCipher.init(Cipher.ENCRYPT_MODE, receiverKeyPair.getPublic());
        byte[] encryptedSessionKey = rsaCipher.doFinal(aesKey.getEncoded());
        System.out.println("Encrypted AES Session Key: " + Base64.getEncoder().encodeToString(encryptedSessionKey));

        // 4️⃣ Nachricht mit AES-GCM verschlüsseln
        String message = "Hallo, dies ist eine geheime Nachricht!";
        Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");

        byte[] nonce = SecureRandom.getInstanceStrong().generateSeed(GCM_NONCE_LENGTH);
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, nonce);
        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey, spec);
        byte[] ciphertext = aesCipher.doFinal(message.getBytes());

        System.out.println("Encrypted message: " + Base64.getEncoder().encodeToString(ciphertext));

        // 5️⃣ Entschlüsseln auf Empfänger-Seite
        // 5a. Session-Key entschlüsseln
        rsaCipher.init(Cipher.DECRYPT_MODE, receiverKeyPair.getPrivate());
        byte[] decryptedSessionKeyBytes = rsaCipher.doFinal(encryptedSessionKey);
        SecretKey decryptedAesKey = new javax.crypto.spec.SecretKeySpec(decryptedSessionKeyBytes, "AES");

        // 5b. Nachricht entschlüsseln
        aesCipher.init(Cipher.DECRYPT_MODE, decryptedAesKey, spec);
        byte[] decryptedMessage = aesCipher.doFinal(ciphertext);

        System.out.println("Decrypted message: " + new String(decryptedMessage));
    }
}
