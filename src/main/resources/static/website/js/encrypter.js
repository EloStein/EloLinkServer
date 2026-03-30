export async function decryptAesKey(privateKey, encryptedAesKeyBytes) {
    const decryptedKeyBuffer = await crypto.subtle.decrypt(
        {name: "RSA-OAEP"}, privateKey, encryptedAesKeyBytes
    );
    return await crypto.subtle.importKey(
        "raw", decryptedKeyBuffer, {name: "AES-GCM"}, false, ["decrypt"]
    );
}

export async function decryptText(encryptedTextBytes, aesKey, ivBytes) {
    const decryptedBuffer = await crypto.subtle.decrypt(
        {name: "AES-GCM", iv: ivBytes}, aesKey, encryptedTextBytes
    );
    return new TextDecoder().decode(decryptedBuffer);
}

export async function encryptAesKey(publicKey, aesKey) {
    const rawAesKey = await crypto.subtle.exportKey("raw", aesKey);
    return await crypto.subtle.encrypt({ name: "RSA-OAEP" }, publicKey, rawAesKey);
}

export async function encryptText(text, aesKey, ivBytes) {
    const encodedText = new TextEncoder().encode(text);
    return await crypto.subtle.encrypt({ name: "AES-GCM", iv: ivBytes }, aesKey, encodedText);
}