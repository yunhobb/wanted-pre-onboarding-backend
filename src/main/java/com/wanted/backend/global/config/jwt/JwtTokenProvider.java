package com.wanted.backend.global.config.jwt;

import com.wanted.backend.global.exception.CustomJwtTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final Key key;
    private static final Long ACCESS_TOKEN_EXPIRE_LENGTH = 60L * 60 * 24 * 1000; // 1 Day

    public boolean validateToken(String token) {
        try {
            return !Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(parseBearer(token))
                .getBody()
                .getExpiration().before(new Date());
        } catch (Exception e) {
            throw new CustomJwtTokenException();
        }
    }

    public MemberTokenInfo parsingTokenToMember(String token) {
        try {
            Claims body = Jwts.parserBuilder()
                .setSigningKey(key).build()
                .parseClaimsJws(parseBearer(token))
                .getBody();

            Long id = Long.parseLong(body.get("id", String.class));
            String email = body.get("email", String.class);

            return new MemberTokenInfo(id, email);
        } catch (RuntimeException e) {
            throw new CustomJwtTokenException();
        }
    }

    public String createJwtAuthToken(Long id, String email) {
        Claims claims = Jwts.claims();
        claims.put("id", id.toString());
        claims.put("email", email);

        long now = new Date().getTime();

        return Jwts.builder()
            .setClaims(claims)
            .signWith(key, SignatureAlgorithm.HS256)
            .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_LENGTH))
            .compact();
    }

    private String parseBearer(String token) {
        return token.replace("Bearer", "").trim();
    }
}
