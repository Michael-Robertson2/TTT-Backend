package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.Role;
import com.revature.ecommerce.entities.dtos.responses.Principal;
import com.revature.ecommerce.utils.utility_classes.JWTConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private JWTConfig jwtConfig;

    public TokenService(JWTConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(Principal subject) {
        long now = System.currentTimeMillis();
        JwtBuilder tokenBuilder = Jwts.builder()
                .setIssuer("ecommerce")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration()))
                .setSubject(subject.getEmail())
                .claim("givenName", subject.getGivenName())
                .claim("surname", subject.getSurname())
                .claim("role", subject.getRole().toString())
                .claim("cardNumber", subject.getCardNumber())
                .claim("expirationDate", (subject.getExpirationDate() == null ? null : subject.getExpirationDate().toString()))
                .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());
        return tokenBuilder.compact();
    }

    public Principal extractRequesterDetails(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();
            return new Principal(claims.get("email", String.class), claims.get("givenName", String.class),
                    claims.get("surname", String.class), claims.get("role", Role.class),
                    claims.get("cardNumber", String.class), claims.get("expirationDate", Date.class));
        } catch (Exception e) {
            return null;
        }
    }
}
