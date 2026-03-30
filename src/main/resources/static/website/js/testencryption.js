import { genRsaKeyPair, genAesKey, genSpec } from "./keygen.js";
import { encryptAesKey, encryptText, decryptAesKey, decryptText } from "./encrypter.js";
import { toBase64, keyToBase64, toPrivateKey, toBytes, toPublicKey } from "./base64encoder.js";

async function main() {

    // RSA Generation
    const receiverRsaKeyPair = await genRsaKeyPair();
    const senderRsaKeyPair = await genRsaKeyPair();

    // AES Key Generation
    const aesKey = await genAesKey();
    console.log("Unencrypted AES Key:", aesKey);

    const encryptedAesKeyRec = await encryptAesKey(receiverRsaKeyPair.publicKey, aesKey);
    const encryptedAesKeyOwn = await encryptAesKey(senderRsaKeyPair.publicKey, aesKey);

    // Private and Public Key to Base64
    const publicKeyBase64Rec = await keyToBase64(receiverRsaKeyPair.publicKey);
    const privateKeyBase64Rec = await keyToBase64(receiverRsaKeyPair.privateKey);
    const publicKeyBase64Own = await keyToBase64(senderRsaKeyPair.publicKey);
    const privateKeyBase64Own = await keyToBase64(senderRsaKeyPair.privateKey);

    // Encrypted AES Key to Base64
    const aesKeyBase64Rec = toBase64(encryptedAesKeyRec);
    const aesKeyBase64Own = toBase64(encryptedAesKeyOwn);

    console.log("AES Key Rec Base64:", aesKeyBase64Rec);
    console.log("AES Key Own Base64:", aesKeyBase64Own);

    // Encrypting Message
    const spec = genSpec();
    const encryptedText = await encryptText("TestNachricht", aesKey, spec.iv);
    console.log("Encrypted Message:", toBase64(encryptedText));

    // Base64 back to encrypted AES key bytes
    const reEncryptedAesKeyRec = toBytes(aesKeyBase64Rec);
    const reEncryptedAesKeyOwn = toBytes(aesKeyBase64Own);

    // Base64 back to Private Keys (Never publish private keys!)
    const reEncryptedPrivateKeyRec = await toPrivateKey(privateKeyBase64Rec);
    const reEncryptedPrivateKeyOwn = await toPrivateKey(privateKeyBase64Own);

    // Decrypting AES Keys
    const decryptedAesKeyRec = await decryptAesKey(
        reEncryptedPrivateKeyRec,
        reEncryptedAesKeyRec
    );

    const decryptedAesKeyOwn = await decryptAesKey(
        reEncryptedPrivateKeyOwn,
        reEncryptedAesKeyOwn
    );

    // Decrypting Message
    const decryptedMessageRec = await decryptText(
        encryptedText,
        decryptedAesKeyRec,
        spec.iv
    );

    const decryptedMessageOwn = await decryptText(
        encryptedText,
        decryptedAesKeyOwn,
        spec.iv
    );

    console.log("Decrypted Message by Rec:", decryptedMessageRec);
    console.log("Decrypted Message by Own:", decryptedMessageOwn);
}

main()