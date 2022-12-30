package com.revature.ecommerce.utils.utility_classes;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

@Component
public class JWTConfig {

    private final int expiration = 60 * 60 * 1000;
    private final SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;
    private final Key signingKey;

    public JWTConfig(@Value("${salt}") String salt) {
        byte[] saltyBytes = DatatypeConverter.parseBase64Binary(salt);
        signingKey = new SecretKeySpec(saltyBytes, sigAlg.getJcaName());
    }

    public int getExpiration() {
        return expiration;
    }

    public SignatureAlgorithm getSigAlg() {
        return sigAlg;
    }

    public Key getSigningKey() {
        return signingKey;
    }
}
