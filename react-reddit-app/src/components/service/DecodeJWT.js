function _decodeBase64Url(base64Url) {
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const json = atob(base64);
    return JSON.parse(json);
}

function decodeJWT(token) {
    try {
        const [headerBase64Url, payloadBase64Url, signatureBase64Url] = token.split('.');

        const header = _decodeBase64Url(headerBase64Url);
        const payload = _decodeBase64Url(payloadBase64Url);
        const signature = _base64UrlToBase64(signatureBase64Url);

        return { header, payload, signature };
    } catch (err) {
        console.error('Failed to decode JWT:', err);
        return null;
    }
}

function _base64UrlToBase64(base64Url) {
    return base64Url.replace(/-/g, '+').replace(/_/g, '/');
}

function extractUserDetails(token) {
    const decoded = decodeJWT(token);
    if (!decoded) return null;
    return decoded.payload;
}

export { decodeJWT, extractUserDetails };
