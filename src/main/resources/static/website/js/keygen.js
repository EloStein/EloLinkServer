export async function genRsaKeyPair() {
    return await crypto.subtle.generateKey({name: "RSA-OAEP", modulusLength: 2048, publicExponent: new Uint8Array([1, 0, 1]), hash: "SHA-256"}, true, ["encrypt", "decrypt"]);
}

export async function genAesKey() {
    return await crypto.subtle.generateKey({name: "AES-GCM", length: 256}, true, ["encrypt", "decrypt"]);
}

export function genSpec() {
    const nonce = crypto.getRandomValues(new Uint8Array(12));
    return {iv: nonce, tagLength: 128};
}