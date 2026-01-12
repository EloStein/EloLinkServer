package de.spring.elolink_spring.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;
import java.util.Random;

public class TestEncryption2 {

    private static final int AES_KEY_SIZE = 256;
    private static final int GCM_NONCE_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 128;

    public static void main(String[] args) throws Exception {
        // ===== 1️⃣ RSA-KeyPairs für Sender & Empfänger =====
        KeyPairGenerator rsaGen = KeyPairGenerator.getInstance("RSA");
        rsaGen.initialize(2048);
        KeyPair senderKeyPair = rsaGen.generateKeyPair();
        KeyPair receiverKeyPair = rsaGen.generateKeyPair();

        // ===== 2️⃣ AES-Key erzeugen =====
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(AES_KEY_SIZE);
        SecretKey aesKey = keyGen.generateKey();

        // ===== 3️⃣ AES-Key per RSA an Empfänger verschlüsseln =====
        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        rsaCipher.init(Cipher.ENCRYPT_MODE, receiverKeyPair.getPublic());
        byte[] encryptedAESKey = rsaCipher.doFinal(aesKey.getEncoded());
        System.out.println("AES-Key verschlüsselt (Base64): " + Base64.getEncoder().encodeToString(encryptedAESKey));

        // Empfänger entschlüsselt den AES-Key
        rsaCipher.init(Cipher.DECRYPT_MODE, receiverKeyPair.getPrivate());
        byte[] decryptedKeyBytes = rsaCipher.doFinal(encryptedAESKey);
        SecretKey receiverAESKey = new SecretKeySpec(decryptedKeyBytes, "AES");

        // ===== 4️⃣ Nachricht verschlüsseln =====
        String message = "Hallo, dies ist eine geheime Nachricht!";
        byte[] nonce = new byte[GCM_NONCE_LENGTH];
        new SecureRandom().nextBytes(nonce);

        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, nonce);
        Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");
        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey, spec);
        byte[] ciphertext = aesCipher.doFinal(message.getBytes());

        System.out.println("Verschlüsselte Nachricht (Base64): " + Base64.getEncoder().encodeToString(ciphertext));

        // ===== 5️⃣ Sender entschlüsselt eigene Nachricht =====
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey, spec);
        byte[] decryptedBySender = aesCipher.doFinal(ciphertext);
        System.out.println("Sender liest: " + new String(decryptedBySender));

        // ===== 6️⃣ Empfänger entschlüsselt Nachricht =====
        aesCipher.init(Cipher.DECRYPT_MODE, receiverAESKey, spec);
        byte[] decryptedByReceiver = aesCipher.doFinal(ciphertext);
        System.out.println("Empfänger liest: " + new String(decryptedByReceiver));
    }
}
