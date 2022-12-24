package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.enums.Role;
import com.revature.ecommerce.entities.dtos.responses.Principal;
import com.revature.ecommerce.utils.custom_exceptions.InvalidAuthException;
import com.revature.ecommerce.utils.utility_classes.JWTConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;

@Service
public class TokenService {

    private JWTConfig jwtConfig;
    private DateFormat dateFormat;

    public TokenService(JWTConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public String generateToken(Principal subject) {
        long now = System.currentTimeMillis();
        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(subject.getId())
                .setIssuer("ecommerce")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration()))
                .setSubject(subject.getEmail())
                .claim("email", subject.getEmail())
                .claim("givenName", subject.getGivenName())
                .claim("surname", subject.getSurname())
                .claim("role", subject.getRole())
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

            return new Principal(claims.getId(), claims.get("email", String.class), claims.get("givenName", String.class),
                    claims.get("surname", String.class), Role.valueOf(claims.get("role", String.class)),
                    claims.get("cardNumber", String.class), (claims.get("expirationDate", String.class) == null ? null : dateFormat.parse(claims.get("expirationDate", String.class))));

        } catch (Exception e) {
            return null;
        }
    }

    public Principal isLoggedIn(HttpServletRequest req) {
        String token = req.getHeader("authorization");
        if (token == null || token.isEmpty()) throw new InvalidAuthException("Invalid Token");

        Principal principal = extractRequesterDetails(token);
        if (principal == null) throw new InvalidAuthException("Please sign in to edit your information");

        return principal;
    }
}
