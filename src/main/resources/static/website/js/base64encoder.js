export function toBytes(base64) {
    const binary = atob(base64);
    const bytes = new Uint8Array(binary.length);
    for (let i = 0; i < binary.length; i++) {
        bytes[i] = binary.charCodeAt(i);
    }
    return bytes;
}

export function toBase64(bytes) {
    const binary = String.fromCharCode(...new Uint8Array(bytes));
    return btoa(binary);
}

export async function keyToBase64(key) {
    const exported = await crypto.subtle.exportKey("pkcs8", key)
        .catch(() => crypto.subtle.exportKey("spki", key));
    return toBase64(exported);
}

export async function toPrivateKey(base64) {
    const bytes = toBytes(base64);
    return await crypto.subtle.importKey("pkcs8", bytes, {name: "RSA-OAEP", hash: "SHA-256"}, true, ["decrypt"]);
}

export async function toPublicKey(base64) {
    const bytes = toBytes(base64);
    return await crypto.subtle.importKey("spki", bytes, {name: "RSA-OAEP", hash: "SHA-256"}, true, ["encrypt"]);
}