package com.yuheng.pangolin.service.token;

import com.yuheng.pangolin.config.TokenConfig;
import com.yuheng.pangolin.model.Token;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class TokenServiceImpl implements TokenService {

    private final TokenConfig tokenConfig;

    @Autowired
    public TokenServiceImpl(TokenConfig tokenConfig) {
        this.tokenConfig = tokenConfig;
    }

    @Override
    public Token createTokenByUserID(String uid) {
        Key key = getHS256Key();
        Date expiration = new Date(System.currentTimeMillis() + tokenConfig.getExpiration() * 1000);
        String tokenString = Jwts.builder()
                .setExpiration(expiration)
                .setSubject(uid)
                .signWith(key)
                .compact();
        return new Token(tokenString);
    }

    @Override
    public Claims parseToken(String token) {
        Key key = getHS256Key();
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean isTokenExpired(String token) {
        Claims claims = parseToken(token);
        return claims.getExpiration().before(new Date());
    }

    @Override
    public String getUserId(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    private Key getHS256Key() {
        SignatureAlgorithm alg = SignatureAlgorithm.HS256;
        String signatureKey = tokenConfig.getSecretKey();
        byte[] bytes = signatureKey.getBytes();
        return new SecretKeySpec(bytes, alg.getJcaName());
    }
}