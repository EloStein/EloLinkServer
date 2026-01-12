package de.spring.elolink_spring.verification;

import java.security.*;
import java.util.Base64;

public class TestVeri {

    public static void main(String[] args) throws Exception {

        // ===== 1️⃣ RSA KeyPair erzeugen =====
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // starke RSA Keys
        KeyPair keyPair = keyGen.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate(); // Sender privat
        PublicKey publicKey = keyPair.getPublic();   // Empfänger/Server kennt das

        // ===== 2️⃣ Nachricht =====
        String message = "Hallo, sichere Nachricht!";

        // ===== 3️⃣ Signatur erstellen =====
        Signature rsaSign = Signature.getInstance("SHA256withRSA");
        rsaSign.initSign(privateKey);
        rsaSign.update(message.getBytes());
        byte[] signatureBytes = rsaSign.sign();

        String signatureBase64 = Base64.getEncoder().encodeToString(signatureBytes);
        System.out.println("Signatur (Base64): " + signatureBase64);

        // ===== 4️⃣ Signatur verifizieren =====
        Signature rsaVerify = Signature.getInstance("SHA256withRSA");
        rsaVerify.initVerify(publicKey);
        rsaVerify.update(message.getBytes());

        boolean isVerified = rsaVerify.verify(signatureBytes);
        System.out.println("Signatur gültig? " + isVerified);

        // ===== 5️⃣ Beispiel: Manipulation =====
        String fakeMessage = "Hallo, falsche Nachricht!";
        rsaVerify.update(fakeMessage.getBytes());
        boolean isVerifiedFake = rsaVerify.verify(signatureBytes);
        System.out.println("Signatur gültig für gefälschte Nachricht? " + isVerifiedFake);
    }
}
